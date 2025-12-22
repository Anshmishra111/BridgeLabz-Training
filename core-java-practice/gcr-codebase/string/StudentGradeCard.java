// package core-java-practice.gcr-codebase.string;

import java.util.Random;
import java.util.Scanner;

public class StudentGradeCard {
    static int[][] generatePCMScores(int students) {
        int[][] scores = new int[students][3];
        Random rand = new Random();

        for (int i = 0; i < students; i++) {
            scores[i][0] = rand.nextInt(90) + 10; 
            scores[i][1] = rand.nextInt(90) + 10; 
            scores[i][2] = rand.nextInt(90) + 10; 
        }
        return scores;
    }
    static double[][] calculateResults(int[][] scores) {
        double[][] result = new double[scores.length][3];

        for (int i = 0; i < scores.length; i++) {
            int total = scores[i][0] + scores[i][1] + scores[i][2];
            double avg = total / 3.0;
            double percent = (total / 300.0) * 100;
            avg = Math.round(avg * 100.0) / 100.0;
            percent = Math.round(percent * 100.0) / 100.0;

            result[i][0] = total;
            result[i][1] = avg;
            result[i][2] = percent;
        }
        return result;
    }
    static String[] calculateGrades(double[][] results) {
        String[] grades = new String[results.length];

        for (int i = 0; i < results.length; i++) {
            double percent = results[i][2];

            if (percent >= 80)
                grades[i] = "A";
            else if (percent >= 70)
                grades[i] = "B";
            else if (percent >= 60)
                grades[i] = "C";
            else if (percent >= 50)
                grades[i] = "D";
            else if (percent >= 40)
                grades[i] = "E";
            else
                grades[i] = "R";
        }
        return grades;
    }
    static void displayScoreCard(int[][] scores, double[][] results, String[] grades) {

        System.out.println("\nID\tPhy\tChem\tMath\tTotal\tAvg\t%\tGrade");
        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < scores.length; i++) {
            System.out.println(
                    (i + 1) + "\t" +
                    scores[i][0] + "\t" +
                    scores[i][1] + "\t" +
                    scores[i][2] + "\t" +
                    (int) results[i][0] + "\t" +
                    results[i][1] + "\t" +
                    results[i][2] + "\t" +
                    grades[i]
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int students = sc.nextInt();
        int[][] scores = generatePCMScores(students);
        double[][] results = calculateResults(scores);
        String[] grades = calculateGrades(results);
        displayScoreCard(scores, results, grades);
        sc.close();
    }
}

    

