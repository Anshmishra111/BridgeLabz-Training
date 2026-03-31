package keywords;

public class VehicleRegistrationSystem {
	    public static void main(String[] args) {

	        // Update registration fee for all vehicles
	        Vehicle.updateRegistrationFee(150.0);

	        // Create Vehicle objects
	        Vehicle v1 = new Vehicle("Honest raj", "Sedan", "ABC123");
	        Vehicle v2 = new Vehicle("Price danish", "SUV", "XYZ789");

	        // Check instanceof before displaying details
	        if (v1 instanceof Vehicle) {
	            v1.displayVehicleDetails();
	        }

	        if (v2 instanceof Vehicle) {
	            v2.displayVehicleDetails();
	        }
	    }
	}

	// -------------------- Vehicle Class --------------------
	class Vehicle {

	    // Static variable (shared registration fee)
	    static double registrationFee = 0.0;

	    // Instance variables
	    String ownerName;
	    String vehicleType;
	    final String registrationNumber; // final (cannot be changed)

	    // Constructor using 'this'
	    Vehicle(String ownerName, String vehicleType, String registrationNumber) {
	        this.ownerName = ownerName;
	        this.vehicleType = vehicleType;
	        this.registrationNumber = registrationNumber;
	    }

	    // Static method to update registration fee
	    static void updateRegistrationFee(double fee) {
	        registrationFee = fee;
	    }

	    // Method to display vehicle details
	    void displayVehicleDetails() {
	        System.out.println("Owner Name: " + ownerName);
	        System.out.println("Vehicle Type: " + vehicleType);
	        System.out.println("Registration Number: " + registrationNumber);
	        System.out.println("Registration Fee: $" + registrationFee);
	    }
	}


