package constructors;

public class HotelBookingSystem {
	    // Attributes
	    String guestName;
	    String roomType;
	    int nights;

	    // Default constructor
	    HotelBookingSystem() {
	        guestName = "Guest";
	        roomType = "Standard";
	        nights = 1;
	    }

	    // Parameterized constructor
	    HotelBookingSystem(String guestName, String roomType, int nights) {
	        this.guestName = guestName;
	        this.roomType = roomType;
	        this.nights = nights;
	    }

	    // Copy constructor
	    HotelBookingSystem(HotelBookingSystem other) {
	        this.guestName = other.guestName;
	        this.roomType = other.roomType;
	        this.nights = other.nights;
	    }

	    // Method to display booking details
	    void displayBooking() {
	        System.out.println("Guest Name: " + guestName);
	        System.out.println("Room Type : " + roomType);
	        System.out.println("Nights    : " + nights);
	        System.out.println("---------------------------");
	    }

	    public static void main(String[] args) {

	        // Booking using default constructor
	    	HotelBookingSystem booking1 = new HotelBookingSystem();
	        booking1.displayBooking();

	        // Booking using parameterized constructor
	        HotelBookingSystem booking2 = new HotelBookingSystem("Ansh", "Deluxe", 3);
	        booking2.displayBooking();

	        // Booking using copy constructor
	        HotelBookingSystem booking3 = new HotelBookingSystem(booking2);
	        booking3.displayBooking();
	    }
	}


