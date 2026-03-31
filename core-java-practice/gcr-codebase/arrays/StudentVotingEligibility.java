// package core-java-practice.gcr-codebase.arrays;

import java.util.Scanner;

public class StudentVotingEligibility {
    public static void main(String[] args) {
        // Implementation for StudentVotingEligibility
        Scanner sc = new Scanner(System.in);
        int[] ages = new int[10];
        for (int i = 0; i < ages.length; i++) {
            System.out.print("Enter age of student " + (i + 1) + ": ");
            ages[i] = sc.nextInt();
        }
        System.out.println("\n--- Voting Eligibility Results ---");
        for (int i = 0; i < ages.length; i++) {

            int age = ages[i];

            if (age < 0) {
                System.out.println("Invalid age entered");
            } else if (age >= 18) {
                System.out.println("The student with the age " + age + " can vote");
            } else {
                System.out.println("The student with the age " + age + " cannot vote");
            }
        }

        sc.close();
    }
}

       