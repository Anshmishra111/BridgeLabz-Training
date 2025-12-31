package constructors;

public class BookLibrarySystem {
	    public static void main(String[] args) {

	        // Create Book object
	        Book book = new Book(
	                "978-0134685991",
	                "Effective Java",
	                "Joshua Bloch"
	        );

	        book.displayBookDetails();

	        // Modify author using setter
	        book.setAuthor("J. Bloch");
	        System.out.println("Updated Author: " + book.getAuthor());

	        // Create EBook object
	        EBook ebook = new EBook(
	                "978-0134685991",
	                "Effective Java",
	                "J. Bloch",
	                "PDF"
	        );

	        ebook.displayEBookDetails();
	    }
	}

	// -------------------- Book Class --------------------
	class Book {

	    public String ISBN;          // public
	    protected String title;      // protected
	    private String author;       // private

	    Book(String ISBN, String title, String author) {
	        this.ISBN = ISBN;
	        this.title = title;
	        this.author = author;
	    }

	    // Setter for private variable
	    public void setAuthor(String author) {
	        this.author = author;
	    }

	    // Getter for private variable
	    public String getAuthor() {
	        return author;
	    }

	    void displayBookDetails() {
	        System.out.println("ISBN   : " + ISBN);
	        System.out.println("Title  : " + title);
	        System.out.println("Author : " + author);
	        System.out.println("---------------------------");
	    }
	}

	// -------------------- EBook Class --------------------
	class EBook extends Book {

	    String fileFormat;

	    EBook(String ISBN, String title, String author, String fileFormat) {
	        super(ISBN, title, author);
	        this.fileFormat = fileFormat;
	    }

	    void displayEBookDetails() {
	        System.out.println("ISBN        : " + ISBN);        // public
	        System.out.println("Title       : " + title);       // protected
	        System.out.println("Author      : " + getAuthor()); // private via getter
	        System.out.println("File Format : " + fileFormat);
	        System.out.println("---------------------------");
	    }
	}


