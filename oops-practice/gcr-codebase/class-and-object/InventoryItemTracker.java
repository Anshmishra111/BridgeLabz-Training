package classAndObject;

public class InventoryItemTracker {
	    // Attributes
	    String itemCode;
	    String itemName;
	    double price;

	    // Constructor
	    InventoryItemTracker(String itemCode, String itemName, double price) {
	        this.itemCode = itemCode;
	        this.itemName = itemName;
	        this.price = price;
	    }

	    // Method to display item details
	    void displayItemDetails() {
	        System.out.println("itemCode : " + itemCode);
	        System.out.println("itemPrice : " + price);
	        System.out.println("itemName : " + itemName);
	        System.out.println("----------------------------");
	    }

	    // Method to calculate total cost for given quantity
	    double calculateTotalCost(int quantity) {
	        return price * quantity;
	    }

	    public static void main(String[] args) {

	        // Create Item objects
	    	InventoryItemTracker item1 = new InventoryItemTracker("01AA", "Water bottle", 500.0);
	    	InventoryItemTracker item2 = new InventoryItemTracker("01BB", "Rice", 700.0);
	        InventoryItemTracker item3 = new InventoryItemTracker("02AA", "blackboard", 400.0);

	        // Display item details
	        item1.displayItemDetails();
	        item2.displayItemDetails();
	        item3.displayItemDetails();

	        // Example usage of total cost method (optional)
	        // System.out.println("Total cost: " + item1.calculateTotalCost(2));
	    }
	}


