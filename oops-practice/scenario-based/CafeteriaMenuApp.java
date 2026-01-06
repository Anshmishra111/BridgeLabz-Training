package scenariobased;
import java.util.Scanner;

public class CafeteriaMenuApp {

	    // Menu items array (10 fixed items)
	    static String[] menuItems = {
	            "Idli",
	            "Dosa",
	            "Vada",
	            "Pongal",
	            "Poori",
	            "Veg Rice",
	            "Sambar Rice",
	            "Curd Rice",
	            "Tea",
	            "Coffee"
	    };

	    // Method to display menu
	    static void displayMenu() {
	        System.out.println("------ Cafeteria Menu ------");
	        for (int i = 0; i < menuItems.length; i++) {
	            System.out.println(i + " : " + menuItems[i]);
	        }
	    }

	    // Method to get item by index
	    static String getItemByIndex(int index) {
	        if (index >= 0 && index < menuItems.length) {
	            return menuItems[index];
	        } else {
	            return "Invalid selection";
	        }
	    }

	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);

	        // Display menu
	        displayMenu();

	        // Take user input
	        System.out.print("\nEnter item index to order: ");
	        int choice = sc.nextInt();

	        // Get selected item
	        String selectedItem = getItemByIndex(choice);

	        System.out.println("You selected: " + selectedItem);

	        sc.close();
	    }
	}


