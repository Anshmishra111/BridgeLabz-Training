package com.firstspringapp.controller;

import com.firstspringapp.model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @PostMapping
    public ResponseEntity<String> createEmployee(
            @Valid @RequestBody Employee employee) {

        return ResponseEntity.ok(
                "Employee created successfully: "
                        + employee.getName());
    }
}