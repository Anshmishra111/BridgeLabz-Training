package constructors;

public class Vehicle {
	    // Instance variables (unique for each vehicle)
	    String ownerName;
	    String vehicleType;

	    // Class variable (shared for all vehicles)
	    static double registrationFee = 5000.0;

	    // Constructor
	    Vehicle(String ownerName, String vehicleType) {
	        this.ownerName = ownerName;
	        this.vehicleType = vehicleType;
	    }

	    // Instance method
	    void displayVehicleDetails() {
	        System.out.println("Owner Name       : " + ownerName);
	        System.out.println("Vehicle Type     : " + vehicleType);
	        System.out.println("Registration Fee : ₹" + registrationFee);
	        System.out.println("-------------------------------");
	    }

	    // Class method to update registration fee
	    static void updateRegistrationFee(double newFee) {
	        registrationFee = newFee;
	    }

	    public static void main(String[] args) {

	        // Create vehicles
	        Vehicle v1 = new Vehicle("Ravi", "Car");
	        Vehicle v2 = new Vehicle("Suresh", "Bike");

	        // Display details before fee update
	        v1.displayVehicleDetails();
	        v2.displayVehicleDetails();

	        // Update registration fee for all vehicles
	        Vehicle.updateRegistrationFee(6500.0);

	        // Display details after fee update
	        v1.displayVehicleDetails();
	        v2.displayVehicleDetails();
	    }
	}


