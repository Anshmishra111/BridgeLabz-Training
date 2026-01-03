package scenariobased;
import java.util.*;
public class LibraryManagementSystem {
static String[] titles= {"Effective Java",
        "Clean Code",
        "Java Programming",
        "Data Structures"};
static String[] authors= {"Joshua Bloch",
        "Robert Martin",
        "Herbert Schildt",
        "Mark Allen Weiss"};
static boolean[] isAvailable= {true,true,true,true};
static void displayBooks() {
	
System.out.println("\\nLibrary Books:");
for(int i=0;i<titles.length;i++) {
	System.out.println("Titles: " + titles[i] + ", Author: " + authors[i]
            + ", Status: " + (isAvailable[i] ? "Available" : "Checked Out"));
	
		
}
	
}
static void searchBook(String keyword) {
    boolean found = false;
    System.out.println("\nSearch Results:");
    for (int i = 0; i < titles.length; i++) {
        if (titles[i].toLowerCase().contains(keyword.toLowerCase())) {
            System.out.println("Title: " + titles[i]
                    + ", Author: " + authors[i]
                    + ", Status: " + (isAvailable[i] ? "Available" : "Checked Out"));
            found = true;
        }
    }
    if (!found) {
        System.out.println("No book found with given title.");
    }
}

// Method to checkout a book
static void checkoutBook(String title) {
    for (int i = 0; i < titles.length; i++) {
        if (titles[i].equalsIgnoreCase(title)) {
            if (isAvailable[i]) {
                isAvailable[i] = false;
                System.out.println("Book checked out successfully: " + titles[i]);
            } else {
                System.out.println("Book already checked out.");
            }
            return;
        }
    }
    System.out.println("Book not found.");
}

public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    displayBooks();

    System.out.print("\nEnter title keyword to search: ");
    String keyword = sc.nextLine();
    searchBook(keyword);

    System.out.print("\nEnter exact title to checkout: ");
    String title = sc.nextLine();
    checkoutBook(title);

    displayBooks();

    sc.close();
}
}

