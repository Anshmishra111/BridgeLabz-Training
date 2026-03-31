package encapsulation_polymorphism;
	abstract class LibraryItem {
	    private String itemId;
	    private String title;
	    private String author;

	    public LibraryItem(String id, String title, String author) {
	        this.itemId = id;
	        this.title = title;
	        this.author = author;
	    }

	    public abstract int getLoanDuration();

	    public void getItemDetails() {
	        System.out.println(title + " by " + author);
	    }
	}

	interface Reservable {
	    void reserveItem();
	    boolean checkAvailability();
	}

	class Book extends LibraryItem implements Reservable {
	    public Book(String id, String title, String author) {
	        super(id, title, author);
	    }

	    public int getLoanDuration() {
	        return 14;
	    }

	    public void reserveItem() {
	        System.out.println("Book reserved.");
	    }

	    public boolean checkAvailability() {
	        return true;
	    }
	}

	public class LibraryManagementSystem {
	    public static void main(String[] args) {
	        LibraryItem book = new Book("B101", "Java Programming", "Herbert Schildt");
	        book.getItemDetails();
	        System.out.println("Loan Duration: " + book.getLoanDuration() + " days");
	    }
	}

