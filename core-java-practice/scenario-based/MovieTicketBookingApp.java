package scenario_based;
import java.util.*;
public class MovieTicketBookingApp {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in );
		while(true) {
			System.out.println("\\nEnter movie type (Action, Comedy, Drama) or 'exit' to stop: ");
			String movieType=sc.nextLine().toLowerCase();
			if(movieType.equals("exit")) {
				System.out.println("Booking closed. Thank you!");
				break;
			}
			double ticketprice=0;
			switch(movieType) {
			case "Action":
				ticketprice=300;
				break;
			case "Comedy":
				ticketprice=250;
				break;
			case "Drama":
				ticketprice=200;
				break;
				default:
					System.out.println("Invalid movie type!");
					continue;
			}
			System.out.println("Enter seat type (gold/silver):");
			String seatType=sc.nextLine().toLowerCase();
			if(seatType.equals("Gold")) {
				ticketprice+=100;
			}else if(seatType.equals("Silver")){
				ticketprice+=50;
			}else {
				System.out.println("Invalid seat type!");
				continue;
			}
			System.out.println("Do you want snacks? (yes/no): ");
			String snacks=sc.nextLine().toLowerCase();
			
			if(snacks.equals("yes")) {
			    ticketprice+=80;
			}
			
			System.out.println("\\n--- Booking Summary ---");
			System.out.println("Movie Type : " + movieType);
			System.out.println("Seat Type  : " + seatType);
			System.out.println("Snacks : " + snacks);
			System.out.println("Total bill : " + ticketprice);
	}
	}

}
