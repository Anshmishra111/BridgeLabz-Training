// package core-java-practice.gcr-codebase.arrays;
import java.util.Scanner;
public class StudentGradeCalculator2d {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int number = input.nextInt();
        int[][] marks = new int[number][3];
        double[] percentage = new double[number];
        char[] grade = new char[number];
        for (int i = 0; i < number; i++) {

            System.out.println("\nEnter marks for Student " + (i + 1));

            System.out.print("Physics: ");
            marks[i][0] = input.nextInt();

            System.out.print("Chemistry: ");
            marks[i][1] = input.nextInt();

            System.out.print("Maths: ");
            marks[i][2] = input.nextInt();
            if (marks[i][0] < 0 || marks[i][1] < 0 || marks[i][2] < 0) {
                System.out.println("Invalid marks! Please enter positive values.");
                i--; 
                continue;
            }
        }        for (int i = 0; i < number; i++) {

            int total = marks[i][0] + marks[i][1] + marks[i][2];
            percentage[i] = total / 3.0;

            if (percentage[i] >= 80) {
                grade[i] = 'A';
            } else if (percentage[i] >= 70) {
                grade[i] = 'B';
            } else if (percentage[i] >= 60) {
                grade[i] = 'C';
            } else if (percentage[i] >= 50) {
                grade[i] = 'D';
            } else if (percentage[i] >= 40) {
                grade[i] = 'E';
            } else {
                grade[i] = 'R';
            }
        }
        System.out.println("\n--- Student Result Report ---");
        for (int i = 0; i < number; i++) {
            System.out.println("Student " + (i + 1) +" | Physics: " + marks[i][0] +" | Chemistry: " + marks[i][1] +" | Maths: " + marks[i][2] +" | Percentage: " + percentage[i] +" | Grade: " + grade[i]
            );
        }

        input.close();
    }
}

    

