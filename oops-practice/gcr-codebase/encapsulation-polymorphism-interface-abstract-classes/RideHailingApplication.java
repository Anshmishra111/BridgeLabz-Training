package encapsulation_polymorphism;
	abstract class RideVehicle {
	    private String vehicleId;
	    private String driverName;
	    private double ratePerKm;

	    public RideVehicle(String id, String driver, double rate) {
	        this.vehicleId = id;
	        this.driverName = driver;
	        this.ratePerKm = rate;
	    }

	    public abstract double calculateFare(double distance);

	    public void getVehicleDetails() {
	        System.out.println("Driver: " + driverName + ", Rate/km: ₹" + ratePerKm);
	    }

	    public double getRatePerKm() {
	        return ratePerKm;
	    }
	}

	interface GPS {
	    String getCurrentLocation();
	    void updateLocation(String newLocation);
	}

	class Auto extends RideVehicle implements GPS {
	    private String currentLocation;

	    public Auto(String id, String driver, double rate) {
	        super(id, driver, rate);
	        this.currentLocation = "Unknown";
	    }

	    public double calculateFare(double distance) {
	        return getRatePerKm() * distance;
	    }

	    public String getCurrentLocation() {
	        return currentLocation;
	    }

	    public void updateLocation(String newLocation) {
	        this.currentLocation = newLocation;
	    }
	}

	public class RideHailingApplication {
	    public static void main(String[] args) {
	        RideVehicle ride = new Auto("V102", "Driver Raj", 15);
	        ride.getVehicleDetails();
	        System.out.println("Fare for 10km: ₹" + ride.calculateFare(10));
	    }
	}

