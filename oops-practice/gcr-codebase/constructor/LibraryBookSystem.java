package constructors;

public class LibraryBookSystem {
	    // Attributes
	    String title;
	    String author;
	    double price;
	    boolean isAvailable;

	    // Constructor
	    LibraryBookSystem(String title, String author, double price) {
	        this.title = title;
	        this.author = author;
	        this.price = price;
	        this.isAvailable = true; // book is available initially
	    }

	    // Method to borrow a book
	    void borrowBook() {
	        if (isAvailable) {
	            isAvailable = false;
	            System.out.println("Book borrowed successfully: " + title);
	        } else {
	            System.out.println("Sorry, the book is currently not available.");
	        }
	    }

	    // Method to display book details
	    void displayBookDetails() {
	        System.out.println("Title       : " + title);
	        System.out.println("Author      : " + author);
	        System.out.println("Price       : " + price);
	        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
	        System.out.println("-----------------------------");
	    }

	    public static void main(String[] args) {

	        // Create Book object
	    	LibraryBookSystem book1 = new LibraryBookSystem("Wings of Fire", "A.P.J Abdul Kalam", 500.0);

	        // Display initial status
	        book1.displayBookDetails();

	        // Borrow the book
	        book1.borrowBook();

	        // Try borrowing again
	        book1.borrowBook();

	        // Display final status
	        book1.displayBookDetails();
	    }
	}

