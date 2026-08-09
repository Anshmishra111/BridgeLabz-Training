package com.greetings.controller;

import com.greetings.dao.GreetingDAO;
import com.greetings.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Spring MVC Controller for Greeting CRUD operations.
 *
 * URL mapping summary:
 *  GET  /greetings/           → list all greetings (home)
 *  GET  /greetings/add        → show create form
 *  POST /greetings/add        → save new greeting
 *  GET  /greetings/view       → view single greeting (?id=)
 *  GET  /greetings/edit       → show edit form (?id=)
 *  POST /greetings/edit       → update greeting
 *  GET  /greetings/delete     → delete greeting (?id=)
 */
@Controller
@RequestMapping("/greetings")
public class GreetingController {

    // ─── DAO Injection ────────────────────────────────────────────────────────

    private final GreetingDAO greetingDAO;

    @Autowired
    public GreetingController(GreetingDAO greetingDAO) {
        this.greetingDAO = greetingDAO;
    }

    // ─── READ: List all greetings ─────────────────────────────────────────────

    /**
     * Displays the home page with a list of all greetings.
     * URL: GET /greetings/
     */
    @GetMapping("/")
    public String listGreetings(Model model) {
        List<Greeting> greetings = greetingDAO.findAll();
        model.addAttribute("greetings", greetings);
        model.addAttribute("totalCount", greetingDAO.count());
        return "home";
    }

    // ─── READ: View single greeting ───────────────────────────────────────────

    /**
     * Displays a single greeting's details.
     * URL: GET /greetings/view?id={id}
     */
    @GetMapping("/view")
    public String viewGreeting(@RequestParam("id") int id, Model model) {
        Greeting greeting = greetingDAO.findById(id);
        if (greeting == null) {
            model.addAttribute("errorMsg", "Greeting with ID " + id + " not found.");
            return "home";
        }
        model.addAttribute("greeting", greeting);
        return "view";
    }

    // ─── CREATE: Show add form ────────────────────────────────────────────────

    /**
     * Shows the form to create a new greeting.
     * URL: GET /greetings/add
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "add";
    }

    /**
     * Handles form submission to save a new greeting.
     * URL: POST /greetings/add
     * Validates that name and message are not blank before saving.
     */
    @PostMapping("/add")
    public String saveGreeting(@ModelAttribute Greeting greeting,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        // Trim whitespace
        String name    = greeting.getName()    != null ? greeting.getName().trim()    : "";
        String message = greeting.getMessage() != null ? greeting.getMessage().trim() : "";

        // Validation
        if (name.isEmpty() || message.isEmpty()) {
            model.addAttribute("greeting", greeting);
            model.addAttribute("errorMsg", "Both Name and Message are required.");
            return "add";
        }
        if (name.length() < 2) {
            model.addAttribute("greeting", greeting);
            model.addAttribute("errorMsg", "Name must be at least 2 characters.");
            return "add";
        }
        if (message.length() < 5) {
            model.addAttribute("greeting", greeting);
            model.addAttribute("errorMsg", "Message must be at least 5 characters.");
            return "add";
        }

        greeting.setName(name);
        greeting.setMessage(message);
        greetingDAO.save(greeting);

        redirectAttributes.addFlashAttribute("successMsg", "Greeting added successfully!");
        return "redirect:/greetings/";
    }

    // ─── UPDATE: Show edit form ───────────────────────────────────────────────

    /**
     * Shows the pre-populated edit form for an existing greeting.
     * URL: GET /greetings/edit?id={id}
     */
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Greeting greeting = greetingDAO.findById(id);
        if (greeting == null) {
            model.addAttribute("greetings", greetingDAO.findAll());
            model.addAttribute("errorMsg", "Greeting with ID " + id + " not found.");
            return "home";
        }
        model.addAttribute("greeting", greeting);
        return "edit";
    }

    /**
     * Handles form submission to update an existing greeting.
     * URL: POST /greetings/edit
     */
    @PostMapping("/edit")
    public String updateGreeting(@ModelAttribute Greeting greeting,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        String name    = greeting.getName()    != null ? greeting.getName().trim()    : "";
        String message = greeting.getMessage() != null ? greeting.getMessage().trim() : "";

        // Validation
        if (name.isEmpty() || message.isEmpty()) {
            model.addAttribute("greeting", greeting);
            model.addAttribute("errorMsg", "Both Name and Message are required.");
            return "edit";
        }

        greeting.setName(name);
        greeting.setMessage(message);
        Greeting updated = greetingDAO.update(greeting);

        if (updated == null) {
            redirectAttributes.addFlashAttribute("errorMsg", "Greeting not found. Update failed.");
        } else {
            redirectAttributes.addFlashAttribute("successMsg", "Greeting updated successfully!");
        }

        return "redirect:/greetings/";
    }

    // ─── DELETE ───────────────────────────────────────────────────────────────

    /**
     * Deletes a greeting by ID and redirects to home.
     * URL: GET /greetings/delete?id={id}
     */
    @GetMapping("/delete")
    public String deleteGreeting(@RequestParam("id") int id,
                                 RedirectAttributes redirectAttributes) {
        boolean deleted = greetingDAO.delete(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMsg", "Greeting deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "Greeting not found. Nothing deleted.");
        }
        return "redirect:/greetings/";
    }
}
