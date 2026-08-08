package com.firstspringapp.controller;

import com.firstspringapp.model.Greeting;
import com.firstspringapp.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    public ResponseEntity<Greeting> createGreeting(
            @RequestParam String message) {

        Greeting greeting = greetingService.createGreeting(message);

        return ResponseEntity.ok(greeting);
    }

    @GetMapping
    public ResponseEntity<List<Greeting>> getAllGreetings() {

        return ResponseEntity.ok(
                greetingService.getAllGreetings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreetingById(
            @PathVariable Long id) {

        Greeting greeting = greetingService.getGreetingById(id);

        if (greeting == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(greeting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGreeting(
            @PathVariable Long id) {

        greetingService.deleteGreeting(id);

        return ResponseEntity.noContent().build();
    }
}