package classAndObject;

public class MovieTicketBookingSystem {
	    // Attributes
	    String movieName;
	    String seatNumber;
	    double price;
	    boolean isBooked;

	    // Constructor
	    MovieTicketBookingSystem() {
	        isBooked = false;
	    }

	    // Method to book a ticket
	    void bookTicket(String movieName, String seatNumber, double price) {
	        if (isBooked) {
	            System.out.println("House full!!! sorry..... Ticket already booked");
	        } else {
	            this.movieName = movieName;
	            this.seatNumber = seatNumber;
	            this.price = price;
	            isBooked = true;

	            System.out.println("Ticket booked for movie: " + movieName);
	            System.out.println("Seat Number: " + seatNumber);
	        }
	    }

	    // Method to display ticket details
	    void displayTicketDetails() {
	        if (!isBooked) {
	            System.out.println("Ticket have not booked yet....");
	        } else {
	            System.out.println("Price: $" + price);
	            System.out.println("Ticket booked for movie: " + movieName);
	            System.out.println("Seat Number: " + seatNumber);
	            System.out.println("Price: $" + price);
	        }
	    }

	    public static void main(String[] args) {

	    	MovieTicketBookingSystem ticket = new MovieTicketBookingSystem();

	        // Ticket not booked yet
	        ticket.displayTicketDetails();

	        // First booking
	        ticket.bookTicket("Dragon", "A10", 120.0);

	        // Trying to book again
	        ticket.bookTicket("Dragon", "A10", 120.0);
	        ticket.bookTicket("Dragon", "A10", 120.0);

	        // Display final ticket details
	        ticket.displayTicketDetails();
	    }
	}



