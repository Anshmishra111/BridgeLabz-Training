package com.firstspringapp.controller;

import org.springframework.web.bind.annotation.*;
import com.firstspringapp.model.User;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    // GET: http://localhost:8080/hello
    @GetMapping(value = {"", "/"})
    public String sayHello() {
        return "Hello From BridgeLabz!!!";
    }

    // GET: http://localhost:8080/hello/query?name=Ansh
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String sayHello(@RequestParam(value = "name") String name) {
        return "Hello " + name + "!";
    }

    // GET: http://localhost:8080/hello/param/Ansh
    @GetMapping("/param/{name}")
    public String sayHelloParam(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    // POST: http://localhost:8080/hello/post
    @PostMapping("/post")
    public String sayHello(@RequestBody User user) {
        return "Hello " + user.getFirstName() + " "
                + user.getLastName() + "!";
    }

    // PUT:
    // http://localhost:8080/hello/put/Ansh?lastName=Mishra
    @PutMapping("/put/{firstName}")
    public String sayHello(
            @PathVariable String firstName,
            @RequestParam(value = "lastName") String lastName) {

        return "Hello " + firstName + " " + lastName + "!";
    }
}

