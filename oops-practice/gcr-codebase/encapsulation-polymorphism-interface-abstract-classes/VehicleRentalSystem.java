package encapsulation_polymorphism;
	abstract class Vehicle {
	    private String vehicleNumber;
	    private String type;
	    private double rentalRate;

	    public Vehicle(String number, String type, double rate) {
	        this.vehicleNumber = number;
	        this.type = type;
	        this.rentalRate = rate;
	    }

	    public abstract double calculateRentalCost(int days);

	    public double getRentalRate() {
	        return rentalRate;
	    }
	}

	interface Insurable {
	    double calculateInsurance();
	    String getInsuranceDetails();
	}

	class Car extends Vehicle implements Insurable {
	    public Car(String number, double rate) {
	        super(number, "Car", rate);
	    }

	    public double calculateRentalCost(int days) {
	        return getRentalRate() * days;
	    }

	    public double calculateInsurance() {
	        return 1000;
	    }

	    public String getInsuranceDetails() {
	        return "Insurance: ₹1000 flat for Car";
	    }
	}

	public class VehicleRentalSystem {
	    public static void main(String[] args) {
	        Vehicle car = new Car("UP32AB1234", 1000);
	        System.out.println("Rental Cost for 3 days: ₹" + car.calculateRentalCost(3));
	        Insurable ins = (Insurable) car;
	        System.out.println(ins.getInsuranceDetails());
	    }
	}

