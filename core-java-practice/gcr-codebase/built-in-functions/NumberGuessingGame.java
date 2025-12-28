package built_in_functions;
import java.util.Scanner;
import java.util.Random;
public class NumberGuessingGame {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        Random random = new Random();
	        int low = 1, high = 100;
	        String feedback;

	        while (true) {
	            int guess = low + random.nextInt(high - low + 1);
	            System.out.println("Computer guesses: " + guess);
	            System.out.print("Is it High, Low, or Correct? ");
	            feedback = scanner.nextLine().toLowerCase();

	            if (feedback.equals("correct")) {
	                System.out.println("Computer guessed it!");
	                break;
	            } else if (feedback.equals("low")) {
	                low = guess + 1;
	            } else if (feedback.equals("high")) {
	                high = guess - 1;
	            }
	        }
	    }
	}

