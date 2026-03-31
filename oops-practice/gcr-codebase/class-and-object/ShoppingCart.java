package classAndObject;

public class ShoppingCart {
	    // Attributes
	    String itemName;
	    double price;
	    int quantity;

	    // Constructor
	    ShoppingCart(String itemName, double price, int quantity) {
	        this.itemName = itemName;
	        this.price = price;
	        this.quantity = quantity;
	    }

	    // Method to add item quantity to the cart
	    void addItem(int qty) {
	        quantity += qty;
	        System.out.println("Added " + qty + " of " + itemName + " to the cart.");
	    }

	    // Method to remove item quantity from the cart
	    void removeItem(int qty) {
	        if (qty <= quantity) {
	            quantity -= qty;
	            System.out.println("Removed " + qty + " of " + itemName + " from the cart.");
	        } else {
	            System.out.println("Cannot remove more items than present in cart.");
	        }
	    }

	    // Method to display total cost
	    void displayTotalCost() {
	        double totalCost = price * quantity;
	        System.out.println("Total cost: $" + totalCost);
	    }

	    // Method to display item details
	    void displayItem() {
	        System.out.println("Item: " + itemName +
	                ", Price: $" + price +
	                ", Quantity: " + quantity);
	    }

	    public static void main(String[] args) {

	        // Create cart item
	    	ShoppingCart cartItem = new ShoppingCart("Laptop", 999.99, 1);

	        // Display initial item
	        cartItem.displayItem();

	        // Add items
	        cartItem.addItem(2);

	        // Remove items
	        cartItem.removeItem(1);

	        // Display total cost
	        cartItem.displayTotalCost();
	    }
	}


