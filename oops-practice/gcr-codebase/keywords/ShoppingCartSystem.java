package keywords;

public class ShoppingCartSystem {
	    public static void main(String[] args) {

	        // Update discount for all products
	        Product.updateDiscount(10.0);

	        // Create Product objects
	        Product p1 = new Product("P001", "Laptop", 1200.0, 5);
	        Product p2 = new Product("P002", "Smartphone", 800.0, 10);

	        // Check instanceof before processing
	        if (p1 instanceof Product) {
	            p1.displayProductDetails();
	        }

	        if (p2 instanceof Product) {
	            p2.displayProductDetails();
	        }
	    }
	}

	// -------------------- Product Class --------------------
	class Product {

	    // Static variable (shared discount)
	    static double discount = 0.0;

	    // Instance variables
	    final String productID;   // final (cannot be changed)
	    String productName;
	    double price;
	    int quantity;

	    // Constructor using 'this'
	    Product(String productID, String productName, double price, int quantity) {
	        this.productID = productID;
	        this.productName = productName;
	        this.price = price;
	        this.quantity = quantity;
	    }

	    // Static method to update discount
	    static void updateDiscount(double newDiscount) {
	        discount = newDiscount;
	    }

	    // Method to calculate price after discount
	    double getPriceAfterDiscount() {
	        return price - (price * discount / 100);
	    }

	    // Method to display product details
	    void displayProductDetails() {
	        System.out.println("Product ID: " + productID);
	        System.out.println("Product Name: " + productName);
	        System.out.println("Price: $" + price);
	        System.out.println("Quantity: " + quantity);
	        System.out.println("Discount: " + discount + "%");
	        System.out.println("Price after Discount: $" + getPriceAfterDiscount());
	    }
	}


