package scenariobased;
import java.util.ArrayList;
import java.util.List;

class Flight {
    int flightId;
    String source;
    String destination;

    public Flight(int flightId, String source, String destination) {
        this.flightId = flightId;
        this.source = source;
        this.destination = destination;
    }

    public void displayFlight() {
        System.out.println("Flight ID : " + flightId +
                           " | From : " + source +
                           " | To : " + destination);
    }
}

class Booking {
    String passengerName;
    Flight flight;

    public Booking(String passengerName, Flight flight) {
        this.passengerName = passengerName;
        this.flight = flight;
    }

    public void displayBooking() {
        System.out.println("Passenger : " + passengerName);
        flight.displayFlight();
    }
}

public class FlightBookingSystem {

    // Array to store available flights
    static Flight[] flights = {
        new Flight(101, "Delhi", "Mumbai"),
        new Flight(102, "Bangalore", "Chennai"),
        new Flight(103, "Delhi", "Pune"),
        new Flight(104, "Mumbai", "Goa")
    };

    // List to store bookings
    static List<Booking> bookings = new ArrayList<>();

    // Search Flights (case-insensitive)
    static void searchFlights(String source, String destination) {
        System.out.println("\nAvailable Flights:");
        boolean found = false;

        for (Flight f : flights) {
            if (f.source.equalsIgnoreCase(source) &&
                f.destination.equalsIgnoreCase(destination)) {
                f.displayFlight();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No flights found.");
        }
    }

    // Book Flight
    static void bookFlight(int flightId, String passengerName) {
        for (Flight f : flights) {
            if (f.flightId == flightId) {
                bookings.add(new Booking(passengerName, f));
                System.out.println("Booking successful!");
                return;
            }
        }
        System.out.println("Invalid Flight ID.");
    }

    // Display All Bookings
    static void displayBookings() {
        System.out.println("\nBooking Details:");
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Booking b : bookings) {
            b.displayBooking();
            System.out.println("-------------------");
        }
    }

    // Main Method
    public static void main(String[] args) {

        // Search Flights
        searchFlights("delhi", "mumbai");

        // Book Flight
        bookFlight(101, "Ansh");
        bookFlight(104, "Rahul");

        // View Bookings
        displayBookings();
    }
}

