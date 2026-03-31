package collectors;

import java.util.*;
import java.util.stream.*;

// Employee class
class Employee {
    String name;
    String department;
    double salary;

    Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

// Main class
public class EmployeeSalaryCategorization {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
            new Employee("Amit", "IT", 50000),
            new Employee("Rahul", "HR", 40000),
            new Employee("Neha", "IT", 60000),
            new Employee("Priya", "HR", 45000),
            new Employee("Suman", "Finance", 55000)
        );

        Map<String, Double> avgSalaryByDept =
            employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.averagingDouble(Employee::getSalary)
                ));

        avgSalaryByDept.forEach(
            (dept, avg) ->
                System.out.println(dept + " : " + avg)
        );
    }
}
