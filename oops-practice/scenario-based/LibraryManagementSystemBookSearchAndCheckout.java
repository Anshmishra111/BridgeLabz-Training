package scenariobased;

	import java.util.ArrayList;
	import java.util.List;

	// Custom Exception
	class BookNotAvailableException extends Exception {
	    public BookNotAvailableException(String message) {
	        super(message);
	    }
	}

	// Book class
	class Book {
	    String title;
	    String author;
	    boolean isAvailable;

	    Book(String title, String author, boolean isAvailable) {
	        this.title = title;
	        this.author = author;
	        this.isAvailable = isAvailable;
	    }
	}

	// Main Library System
	public class LibraryManagementSystemBookSearchAndCheckout {

	    static List<Book> bookList = new ArrayList<>();

	    // Method to display all books
	    static void displayBooks() {
	        System.out.println("\nLibrary Books:");
	        for (Book b : bookList) {
	            System.out.println(
	                    "Title: " + b.title +
	                    ", Author: " + b.author +
	                    ", Status: " + (b.isAvailable ? "Available" : "Checked Out")
	            );
	        }
	    }

	    // Method to search by partial title
	    static void searchBook(String keyword) {
	        System.out.println("\nSearch Results:");
	        boolean found = false;

	        for (Book b : bookList) {
	            if (b.title.toLowerCase().contains(keyword.toLowerCase())) {
	                System.out.println(
	                        "Title: " + b.title +
	                        ", Author: " + b.author +
	                        ", Status: " + (b.isAvailable ? "Available" : "Checked Out")
	                );
	                found = true;
	            }
	        }

	        if (!found) {
	            System.out.println("No book found with given title.");
	        }
	    }

	    // Method to checkout a book
	    static void checkoutBook(String title)
	            throws BookNotAvailableException {

	        for (Book b : bookList) {
	            if (b.title.equalsIgnoreCase(title)) {
	                if (!b.isAvailable) {
	                    throw new BookNotAvailableException(
	                            "Book is already checked out: " + title);
	                }
	                b.isAvailable = false;
	                System.out.println("Book checked out successfully: " + title);
	                return;
	            }
	        }
	        System.out.println("Book not found.");
	    }

	    public static void main(String[] args) {

	        // Add books to List
	        bookList.add(new Book("Effective Java", "Joshua Bloch", true));
	        bookList.add(new Book("Clean Code", "Robert Martin", true));
	        bookList.add(new Book("Java Programming", "Herbert Schildt", true));

	        // Store book data in Array
	        Book[] bookArray = bookList.toArray(new Book[0]);

	        displayBooks();

	        // Search book
	        searchBook("java");

	        // Checkout book
	        try {
	            checkoutBook("Clean Code");
	            checkoutBook("Clean Code"); // triggers exception
	        } catch (BookNotAvailableException e) {
	            System.out.println("Error: " + e.getMessage());
	        }

	        displayBooks();
	    }
	}


