package com.payroll;

import com.payroll.dto.Employee;
import com.payroll.repository.EmployeeRepository;
import com.payroll.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class EmployeePayrollApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    @DisplayName("Application context loads successfully")
    void contextLoads() {
    }

    @Test
    @DisplayName("Save and retrieve a single employee by ID")
    void saveAndFindById() {
        Employee emp = Employee.builder()
                .name("Himanshu Mishra")
                .department("Engineering")
                .salary(new BigDecimal("75000.00"))
                .build();

        Employee saved = employeeService.save(emp);
        assertThat(saved.getId()).isNotNull();

        Employee found = employeeService.findById(saved.getId());
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Himanshu Mishra");
        assertThat(found.getDepartment()).isEqualTo("Engineering");
    }

    @Test
    @DisplayName("findAll returns all saved employees")
    void findAll() {
        employeeService.save(Employee.builder()
                .name("Alice")
                .department("HR")
                .salary(new BigDecimal("50000"))
                .build());
        employeeService.save(Employee.builder()
                .name("Bob")
                .department("Engineering")
                .salary(new BigDecimal("90000"))
                .build());

        List<Employee> all = employeeService.findAll();
        assertThat(all).hasSize(2);
    }

    @Test
    @DisplayName("findByDepartment returns only matching employees")
    void findByDepartment() {
        employeeService.save(Employee.builder()
                .name("Alice")
                .department("HR")
                .salary(new BigDecimal("50000"))
                .build());
        employeeService.save(Employee.builder()
                .name("Bob")
                .department("Engineering")
                .salary(new BigDecimal("90000"))
                .build());

        List<Employee> hrEmployees = employeeService.findByDepartment("HR");
        assertThat(hrEmployees).hasSize(1);
        assertThat(hrEmployees.get(0).getName()).isEqualTo("Alice");
    }

    @Test
    @DisplayName("findBySalaryGreaterThan returns only high earners")
    void findBySalaryGreaterThan() {
        employeeService.save(Employee.builder()
                .name("Alice")
                .department("HR")
                .salary(new BigDecimal("50000"))
                .build());
        employeeService.save(Employee.builder()
                .name("Bob")
                .department("Engineering")
                .salary(new BigDecimal("90000"))
                .build());

        List<Employee> highEarners = employeeService.findBySalaryGreaterThan(new BigDecimal("60000"));
        assertThat(highEarners).hasSize(1);
        assertThat(highEarners.get(0).getName()).isEqualTo("Bob");
    }

    @Test
    @DisplayName("save throws IllegalArgumentException when salary is zero or negative")
    void saveRejectNonPositiveSalary() {
        Employee emp = Employee.builder()
                .name("Invalid")
                .department("Finance")
                .salary(BigDecimal.ZERO)
                .build();

        assertThatThrownBy(() -> employeeService.save(emp))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Salary must be positive");
    }

    @Test
    @DisplayName("findById returns null for a non-existent ID")
    void findByIdReturnsNullWhenNotFound() {
        Employee found = employeeService.findById(999);
        assertThat(found).isNull();
    }
}
