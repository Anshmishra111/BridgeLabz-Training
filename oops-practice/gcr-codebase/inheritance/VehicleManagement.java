package inheritance;

	interface Refuelable {
	    void refuel();
	}

	class BaseVehicle {
	    int maxSpeed;
	    String model;

	    BaseVehicle(int speed, String model) {
	        this.maxSpeed = speed;
	        this.model = model;
	    }
	}

	class ElectricVehicle extends BaseVehicle {
	    ElectricVehicle(int speed, String model) {
	        super(speed, model);
	    }

	    void charge() {
	        System.out.println("Charging electric vehicle...");
	    }
	}

	class PetrolVehicle extends BaseVehicle implements Refuelable {
	    PetrolVehicle(int speed, String model) {
	        super(speed, model);
	    }

	    public void refuel() {
	        System.out.println("Refueling petrol vehicle...");
	    }
	}

