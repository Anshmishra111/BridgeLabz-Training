package encapsulation_polymorphism;

	abstract class Product {
	    private String productId;
	    private String name;
	    private double price;

	    public Product(String id, String name, double price) {
	        this.productId = id;
	        this.name = name;
	        this.price = price;
	    }

	    public abstract double calculateDiscount();

	    public double getPrice() {
	        return price;
	    }

	    public String getName() {
	        return name;
	    }
	}

	interface Taxable {
	    double calculateTax();
	    String getTaxDetails();
	}

	class Electronics extends Product implements Taxable {
	    public Electronics(String id, String name, double price) {
	        super(id, name, price);
	    }

	    public double calculateDiscount() {
	        return 0.10 * getPrice();
	    }

	    public double calculateTax() {
	        return 0.18 * getPrice();
	    }

	    public String getTaxDetails() {
	        return "GST: 18%";
	    }
	}

	class Platform {
	    public static void printFinalPrice(Product p) {
	        double discount = p.calculateDiscount();
	        double tax = (p instanceof Taxable) ? ((Taxable) p).calculateTax() : 0;
	        double finalPrice = p.getPrice() + tax - discount;
	        System.out.println(p.getName() + " Final Price: ₹" + finalPrice);
	    }
	}

	public class EcommercePlatform {
	    public static void main(String[] args) {
	        Product p1 = new Electronics("P201", "Laptop", 60000);
	        Platform.printFinalPrice(p1);
	    }
	}

