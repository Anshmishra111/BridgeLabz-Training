// package core-java-practice.gcr-codebase.arrays;
import java.util.Scanner;
public class ZaraEmployeeBonus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] salary = new double[10];
        double[] yearsOfService = new double[10];
        double[] bonus = new double[10];
        double[] newSalary = new double[10];

        double totalBonus = 0.0;
        double totalOldSalary = 0.0;
        double totalNewSalary = 0.0;
        for (int i = 0; i < 10; i++) {

            System.out.print("Enter salary of employee " + (i + 1) + ": ");
            salary[i] = sc.nextDouble();

            System.out.print("Enter years of service of employee " + (i + 1) + ": ");
            yearsOfService[i] = sc.nextDouble();
            if (salary[i] <= 0 || yearsOfService[i] < 0) {
                System.out.println("Invalid sc! Please enter again.");
                i--; 
            }
        }
        for (int i = 0; i < 10; i++) {

            totalOldSalary += salary[i];

            if (yearsOfService[i] > 5) {
                bonus[i] = salary[i] * 0.05; 
            } else {
                bonus[i] = salary[i] * 0.02; 
            }

            newSalary[i] = salary[i] + bonus[i];

            totalBonus += bonus[i];
            totalNewSalary += newSalary[i];
        }
        System.out.println("\n--- Zara Bonus Summary ---");
        System.out.println("Total Old Salary  : INR " + totalOldSalary);
        System.out.println("Total Bonus Paid  : INR " + totalBonus);
        System.out.println("Total New Salary  : INR " + totalNewSalary);

        sc.close();
    }
}
