package scenario_based;
import java.util.Scanner;
public class BusRouteDistanceTracker {
	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);
	        int totalDistance = 0;
	        int stopDistance = 5; 
	        String choice;

	        System.out.println("Bus Route Distance Tracker");
	        System.out.println("Each stop adds " + stopDistance + " km.");
	        while (true) {

	            totalDistance = totalDistance + stopDistance;
	            System.out.println("Bus reached next stop.");
	            System.out.println("Total distance travelled: " + totalDistance + " km");
	            System.out.print("Do you want to get off at this stop? (yes/no): ");
	            choice = sc.next();
	            if (choice.equalsIgnoreCase("yes")) {
	                break; 
	            }
	        }
	        System.out.println("\nPassenger got off the bus.");
	        System.out.println("Total distance travelled: " + totalDistance + " km");

	        sc.close();
	    }
	}


