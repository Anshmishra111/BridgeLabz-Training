package com.payroll.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Full name of the employee. Cannot be blank. */
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    /** Department the employee belongs to. Cannot be blank. */
    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.01", message = "Salary must be positive")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salary;
}
