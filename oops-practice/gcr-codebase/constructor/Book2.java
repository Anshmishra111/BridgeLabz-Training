package constructors;

public class Book2 {
	    // Attributes
	    String title;
	    String author;
	    double price;

	    // Default constructor
	    Book2() {
	        title = "Unknown";
	        author = "Unknown";
	        price = 0.0;
	    }

	    // Parameterized constructor
	    Book2(String title, String author, double price) {
	        this.title = title;
	        this.author = author;
	        this.price = price;
	    }

	    // Method to display book details
	    void displayDetails() {
	        System.out.println("Title: " + title);
	        System.out.println("Author: " + author);
	        System.out.println("Price: " + price);
	        System.out.println("----------------------");
	    }

	    public static void main(String[] args) {

	        // Using default constructor
	    	Book2 book1 = new Book2();
	        book1.displayDetails();

	        // Using parameterized constructor
	        Book2 book2 = new Book2("2 States", "Chetan Bhagat", 500.0);
	        book2.displayDetails();
	    }
	}


