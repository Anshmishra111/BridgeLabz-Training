package constructors;

public class CarRentalSystem {
	    // Attributes
	    String customerName;
	    String carModel;
	    int rentalDays;

	    // Cost per day (constant)
	    static final double COST_PER_DAY = 1500.0;

	    // Default constructor
	    CarRentalSystem() {
	        customerName = "Guest";
	        carModel = "Standard";
	        rentalDays = 1;
	    }

	    // Parameterized constructor
	    CarRentalSystem(String customerName, String carModel, int rentalDays) {
	        this.customerName = customerName;
	        this.carModel = carModel;
	        this.rentalDays = rentalDays;
	    }

	    // Method to calculate total rental cost
	    double calculateTotalCost() {
	        return rentalDays * COST_PER_DAY;
	    }

	    // Method to display rental details
	    void displayRentalDetails() {
	        System.out.println("Customer Name : " + customerName);
	        System.out.println("Car Model     : " + carModel);
	        System.out.println("Rental Days   : " + rentalDays);
	        System.out.println("Total Cost    : ₹" + calculateTotalCost());
	        System.out.println("-------------------------------");
	    }

	    public static void main(String[] args) {

	        // Rental using default constructor
	    	CarRentalSystem rental1 = new CarRentalSystem();
	        rental1.displayRentalDetails();

	        // Rental using parameterized constructor
	        CarRentalSystem rental2 = new CarRentalSystem("Ansh", "Hyundai Creta", 5);
	        rental2.displayRentalDetails();
	    }
	}


