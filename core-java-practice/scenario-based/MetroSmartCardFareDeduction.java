package scenario_based;
import java.util.Scanner;
public class MetroSmartCardFareDeduction {
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 double balance = 200.0; 
	        int choice;
	        System.out.println("Welcome to Delhi Metro Smart Card System");
	        System.out.println("Initial Balance: ₹" + balance);
	        while (balance > 0) {
	            System.out.print("\nEnter distance in km (Enter -1 to quit): ");
	            int distance = sc.nextInt();
	            if (distance == -1) {
	                break;
	            }
	            double fare = (distance <= 5) ? 20 :
	                          (distance <= 15) ? 40 : 60;
	            if (balance >= fare) {
	                balance -= fare;
	                System.out.println("Fare: ₹" + fare);
	                System.out.println("Remaining Balance: ₹" + balance);
	            } else {
	                System.out.println("Insufficient balance! Please recharge.");
	                break;
	            }
	        }

	        System.out.println("\nThank you for using Delhi Metro!");
	        System.out.println("Final Balance: ₹" + balance);

	        sc.close();
    }
}



