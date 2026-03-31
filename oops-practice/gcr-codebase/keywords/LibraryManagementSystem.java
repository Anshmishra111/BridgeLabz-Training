package keywords;

public class LibraryManagementSystem {
	    public static void main(String[] args) {

	        // Display library name using static method
	        Book.displayLibraryName();

	        // Create Book object
	        Book book = new Book(
	                "Effective Java",
	                "Joshua Bloch",
	                "978-0134685991"
	        );

	        // Check using instanceof before displaying details
	        if (book instanceof Book) {
	            book.displayBookDetails();
	        }
	    }
	}

	// -------------------- Book Class --------------------
	class Book {

	    // Static variable (shared across all books)
	    static String libraryName = "Egmore Library";

	    // Instance variables
	    String title;
	    String author;

	    // Final variable (cannot be changed)
	    final String isbn;

	    // Constructor using 'this'
	    Book(String title, String author, String isbn) {
	        this.title = title;
	        this.author = author;
	        this.isbn = isbn;
	    }

	    // Static method
	    static void displayLibraryName() {
	        System.out.println("Library Name: " + libraryName);
	    }

	    // Method to display book details
	    void displayBookDetails() {
	        System.out.println("Title: " + title);
	        System.out.println("Author: " + author);
	        System.out.println("ISBN: " + isbn);
	    }
	}



