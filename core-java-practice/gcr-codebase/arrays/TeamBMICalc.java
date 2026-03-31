// package core-java-practice.gcr-codebase.arrays;
import java.util.Scanner;
public class TeamBMICalc {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Step 1: Take sc for number of persons
        System.out.print("Enter number of persons: ");
        int n = sc.nextInt();
        double[] weight = new double[n];   // weight in kg
        double[] height = new double[n];   // height in meters
        double[] bmi = new double[n];
        String[] status = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight (kg) of person " + (i + 1) + ": ");
            weight[i] = sc.nextDouble();

            System.out.print("Enter height (meters) of person " + (i + 1) + ": ");
            height[i] = sc.nextDouble();
        }
        for (int i = 0; i < n; i++) {

            bmi[i] = weight[i] / (height[i] * height[i]);

            if (bmi[i] <= 18.4) {
                status[i] = "Underweight";
            } else if (bmi[i] >= 18.5 && bmi[i] <= 24.9) {
                status[i] = "Normal";
            } else if (bmi[i] >= 25.0 && bmi[i] <= 39.9) {
                status[i] = "Overweight";
            } else {
                status[i] = "Obese";
            }
        }
        System.out.println("\n--- BMI Report ---");
        for (int i = 0; i < n; i++) {
            System.out.println("Person " + (i + 1) +" | Height: " + height[i] + " m" +" | Weight: " + weight[i] + " kg" +" | BMI: " + bmi[i] +" | Status: " + status[i]);
        }

        sc.close();
    }
}

    

