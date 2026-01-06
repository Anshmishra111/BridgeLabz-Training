package encapsulation_polymorphism;
	abstract class FoodItem {
	    private String itemName;
	    private double price;
	    private int quantity;

	    public FoodItem(String name, double price, int qty) {
	        this.itemName = name;
	        this.price = price;
	        this.quantity = qty;
	    }

	    public abstract double calculateTotalPrice();

	    public void getItemDetails() {
	        System.out.println(itemName + ": ₹" + price + " x " + quantity);
	    }

	    public double getPrice() {
	        return price;
	    }

	    public int getQuantity() {
	        return quantity;
	    }
	}

	interface Discountable {
	    double applyDiscount();
	    String getDiscountDetails();
	}

	class NonVegItem extends FoodItem implements Discountable {
	    public NonVegItem(String name, double price, int qty) {
	        super(name, price, qty);
	    }

	    public double calculateTotalPrice() {
	        return (getPrice() * getQuantity()) + 20; // extra charge
	    }

	    public double applyDiscount() {
	        return 0.05 * calculateTotalPrice();
	    }

	    public String getDiscountDetails() {
	        return "5% off on Non-Veg";
	    }
	}

	public class OnlineFoodDeliverySystem {
	    public static void main(String[] args) {
	        FoodItem item = new NonVegItem("Chicken Biryani", 250, 2);
	        item.getItemDetails();
	        System.out.println("Total Price: ₹" + item.calculateTotalPrice());
	        Discountable dis = (Discountable) item;
	        System.out.println(dis.getDiscountDetails());
	    }
	}

