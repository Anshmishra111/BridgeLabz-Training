package classAndObject;

public class MobilePhoneDetails {
	    // Attributes
	    String brand;
	    String model;
	    double price;

	    // Constructor
	    MobilePhoneDetails(String brand, String model, double price) {
	        this.brand = brand;
	        this.model = model;
	        this.price = price;
	    }

	    // Method to display mobile details
	    void displayDetails() {
	        System.out.println("Brand of mobile: " + brand);
	        System.out.println("Model of mobile: " + model);
	        System.out.println("Price of mobile: " + price);
	        System.out.println("------------------------------");
	    }

	    public static void main(String[] args) {

	        // Create MobilePhone objects
	    	MobilePhoneDetails phone1 = new MobilePhoneDetails("VIVO", "VIVO V29", 15999.0);
	    	MobilePhoneDetails phone2 = new MobilePhoneDetails("ONE PLUS", "ONE PLUS nord4", 39999.0);
	    	MobilePhoneDetails phone3 = new MobilePhoneDetails("APPLE", "iphone pro16", 79999.0);

	        // Display mobile details
	        phone1.displayDetails();
	        phone2.displayDetails();
	        phone3.displayDetails();
	    }
	}


