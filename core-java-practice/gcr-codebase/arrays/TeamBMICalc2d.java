// package core-java-practice.gcr-codebase.arrays;
import java.util.Scanner;
public class TeamBMICalc2d {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of persons: ");
        int number = sc.nextInt();
        double[][] personData = new double[number][3];
        String[] weightStatus = new String[number];
        for (int i = 0; i < number; i++) {

            System.out.print("Enter height (in meters) of person " + (i + 1) + ": ");
            double height = sc.nextDouble();

            System.out.print("Enter weight (in kg) of person " + (i + 1) + ": ");
            double weight = sc.nextDouble();
            if (height <= 0 || weight <= 0) {
                System.out.println("Invalid sc! Please enter positive values.");
                i--; 
                continue;
            }

            personData[i][0] = height;
            personData[i][1] = weight;
        }
        for (int i = 0; i < number; i++) {

            double height = personData[i][0];
            double weight = personData[i][1];

            double bmi = weight / (height * height);
            personData[i][2] = bmi;

            if (bmi <= 18.4) {
                weightStatus[i] = "Underweight";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                weightStatus[i] = "Normal";
            } else if (bmi >= 25.0 && bmi <= 39.9) {
                weightStatus[i] = "Overweight";
            } else {
                weightStatus[i] = "Obese";
            }
        }
        System.out.println("\n--- BMI Report ---");
        for (int i = 0; i < number; i++) {
            System.out.println("Person " + (i + 1) +" | Height: " + personData[i][0] + " m" +" | Weight: " + personData[i][1] + " kg" +" | BMI: " + personData[i][2] + " | Status: " + weightStatus[i]
            );
        }

        sc.close();
    }
}


