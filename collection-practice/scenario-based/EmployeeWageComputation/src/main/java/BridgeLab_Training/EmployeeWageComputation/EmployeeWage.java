package BridgeLab_Training.EmployeeWageComputation;

import java.util.*;

public class EmployeeWage {

    static final int IS_ABSENT = 0;
    static final int IS_FULL_TIME = 1;
    static final int IS_PART_TIME = 2;

    static final int FULL_TIME_HOURS = 8;
    static final int PART_TIME_HOURS = 4;

    static class Company {
        String name;
        int wagePerHour;
        int workingDays;
        int maxHours;

        Company(String name, int wagePerHour, int workingDays, int maxHours) {
            this.name = name;
            this.wagePerHour = wagePerHour;
            this.workingDays = workingDays;
            this.maxHours = maxHours;
        }
    }

    public static void main(String[] args) {

        System.out.println("Welcome to Employee Wage Computation Program"); // UC-1

        List<Company> companies = new ArrayList<>();     // UC-12
        Map<String, Integer> wageMap = new HashMap<>();  // UC-9 / UC-14

        // UC-8 → Multiple Companies
        companies.add(new Company("TCS", 20, 20, 100));
        companies.add(new Company("Infosys", 25, 22, 120));

        for (Company company : companies) {

            int totalHours = 0;
            int totalDays = 0;
            int totalWage = 0;

            // UC-6 → Condition based calculation
            while (totalHours < company.maxHours && totalDays < company.workingDays) {

                totalDays++;

                int empCheck = (int) (Math.random() * 3); // UC-1
                int hoursWorked;

                // UC-4 → Switch Case
                switch (empCheck) {
                    case IS_FULL_TIME:
                        hoursWorked = FULL_TIME_HOURS; // UC-2
                        break;
                    case IS_PART_TIME:
                        hoursWorked = PART_TIME_HOURS; // UC-3
                        break;
                    default:
                        hoursWorked = 0;
                }

                totalHours += hoursWorked;
                int dailyWage = hoursWorked * company.wagePerHour; // UC-13
                totalWage += dailyWage;
            }

            wageMap.put(company.name, totalWage); // UC-9

            System.out.println(company.name + " Total Wage: " + totalWage); // UC-5
        }

        // UC-14 → Query Wage
        System.out.println("\nQueried Wage:");
        System.out.println("TCS Wage: " + wageMap.get("TCS"));
    }
}
