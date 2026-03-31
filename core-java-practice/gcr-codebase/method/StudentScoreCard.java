package method;
import java.util.Scanner;
import java.util.Random;
public class StudentScoreCard {
	
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int n = sc.nextInt();

	        int[][] scores = generateScores(n);
	        double[][] results = calculateResults(scores);
	        displayScoreCard(scores, results);
	        sc.close();
	    }

	    static int[][] generateScores(int n) {
	        Random rand = new Random();
	        int[][] scores = new int[n][3];
	        for (int i = 0; i < n; i++) {
	            scores[i][0] = 40 + rand.nextInt(60);
	            scores[i][1] = 40 + rand.nextInt(60);
	            scores[i][2] = 40 + rand.nextInt(60);
	        }
	        return scores;
	    }

	    static double[][] calculateResults(int[][] scores) {
	        int n = scores.length;
	        double[][] result = new double[n][3];
	        for (int i = 0; i < n; i++) {
	            int total = scores[i][0] + scores[i][1] + scores[i][2];
	            double average = total / 3.0;
	            double percentage = total / 3.0;
	            result[i][0] = Math.round(total);
	            result[i][1] = Math.round(average * 100.0) / 100.0;
	            result[i][2] = Math.round(percentage * 100.0) / 100.0;
	        }
	        return result;
	    }

	    static void displayScoreCard(int[][] scores, double[][] results) {
	        System.out.println("ID\tPhysics\tChemistry\tMaths\tTotal\tAverage\tPercentage\tGrade");
	        for (int i = 0; i < scores.length; i++) {
	            System.out.print((i + 1) + "\t");
	            System.out.print(scores[i][0] + "\t" + scores[i][1] + "\t\t" + scores[i][2] + "\t");
	            System.out.print((int) results[i][0] + "\t" + results[i][1] + "\t" + results[i][2] + "\t\t");
	            System.out.println(getGrade(results[i][2]));
	        }
	    }

	    static String getGrade(double percentage) {
	        if (percentage >= 80) return "A";
	        else if (percentage >= 70) return "B";
	        else if (percentage >= 60) return "C";
	        else if (percentage >= 50) return "D";
	        else if (percentage >= 40) return "E";
	        else return "R";
	    }
	}


