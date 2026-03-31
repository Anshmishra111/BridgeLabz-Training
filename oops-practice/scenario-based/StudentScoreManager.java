package scenariobased;
import java.util.Scanner;
public class StudentScoreManager {
	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);

	        int n;

	        // Validate number of students
	        while (true) {
	            System.out.print("Enter number of students: ");
	            if (sc.hasNextInt()) {
	                n = sc.nextInt();
	                if (n > 0) {
	                    break;
	                } else {
	                    System.out.println("Number must be greater than 0.");
	                }
	            } else {
	                System.out.println("Invalid input. Please enter a number.");
	                sc.next(); // discard invalid input
	            }
	        }

	        int[] scores = new int[n];
	        int sum = 0;

	        // Input scores with validation
	        for (int i = 0; i < n; i++) {
	            while (true) {
	                System.out.print("Enter score for student " + (i + 1) + ": ");
	                if (sc.hasNextInt()) {
	                    int score = sc.nextInt();
	                    if (score >= 0) {
	                        scores[i] = score;
	                        sum += score;
	                        break;
	                    } else {
	                        System.out.println("Score cannot be negative.");
	                    }
	                } else {
	                    System.out.println("Invalid input. Please enter a numeric score.");
	                    sc.next(); // discard invalid input
	                }
	            }
	        }

	        // Calculate average
	        double average = (double) sum / n;

	        // Find highest and lowest
	        int highest = scores[0];
	        int lowest = scores[0];

	        for (int score : scores) {
	            if (score > highest) {
	                highest = score;
	            }
	            if (score < lowest) {
	                lowest = score;
	            }
	        }

	        // Display results
	        System.out.println("\nAverage Score: " + average);
	        System.out.println("Highest Score: " + highest);
	        System.out.println("Lowest Score : " + lowest);

	        System.out.println("Scores above average:");
	        for (int score : scores) {
	            if (score > average) {
	                System.out.println(score);
	            }
	        }

	        sc.close();
	    }
	}


