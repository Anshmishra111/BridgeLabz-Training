package inheritance;

public class VehicleTransportSystem {
	    int maxSpeed;
	    String fuelType;

	    VehicleTransportSystem(int maxSpeed, String fuelType) {
	        this.maxSpeed = maxSpeed;
	        this.fuelType = fuelType;
	    }

	    void displayInfo() {
	        System.out.println("Speed: " + maxSpeed + " km/h, Fuel: " + fuelType);
	    }
	}

	class Car extends VehicleTransportSystem {
	    int seatCapacity;

	    Car(int maxSpeed, String fuelType, int seatCapacity) {
	        super(maxSpeed, fuelType);
	        this.seatCapacity = seatCapacity;
	    }

	    @Override
	    void displayInfo() {
	        super.displayInfo();
	        System.out.println("Seat Capacity: " + seatCapacity);
	    }
	}

	class Truck extends VehicleTransportSystem {
	    double loadCapacity;

	    Truck(int maxSpeed, String fuelType, double loadCapacity) {
	        super(maxSpeed, fuelType);
	        this.loadCapacity = loadCapacity;
	    }

	    @Override
	    void displayInfo() {
	        super.displayInfo();
	        System.out.println("Load Capacity: " + loadCapacity + " tons");
	    }
	}

	class Motorcycle extends VehicleTransportSystem {
	    boolean hasCarrier;

	    Motorcycle(int maxSpeed, String fuelType, boolean hasCarrier) {
	        super(maxSpeed, fuelType);
	        this.hasCarrier = hasCarrier;
	    }

	    @Override
	    void displayInfo() {
	        super.displayInfo();
	        System.out.println("Carrier: " + (hasCarrier ? "Yes" : "No"));
	    }
	}

