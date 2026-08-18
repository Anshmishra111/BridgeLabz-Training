package com.payroll.service;

import com.payroll.dto.Employee;
import com.payroll.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        return result.orElse(null);
    }

    public Employee save(Employee employee) {
        if (employee.getSalary().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salary must be positive");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> findBySalaryGreaterThan(BigDecimal minSalary) {
        return employeeRepository.findBySalaryGreaterThan(minSalary);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
