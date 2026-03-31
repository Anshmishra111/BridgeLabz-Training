package scenario_based;
import java.util.Scanner;
public class FestivalLuckyDraw {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int number;

	        System.out.println("Welcome to Diwali Mela Lucky Draw ");
	        System.out.println("Enter -1 to stop the lucky draw.");
	        while (true) {

	            System.out.print("\nEnter your lucky number: ");
	            number = sc.nextInt();
	            if (number == -1) {
	                break;
	            }
	            if (number <= 0) {
	                System.out.println("Invalid number! Please try again.");
	                continue; 
	            }
	            if (number % 3 == 0 && number % 5 == 0) {
	                System.out.println("Congratulations! You won a gift!");
	            } else {
	                System.out.println("Better luck next time!");
	            }
	        }

	        System.out.println("\nLucky Draw Ended. Happy Diwali! ");
	        sc.close();
	    }
	}



