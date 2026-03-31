package linkedlist;

public class LibraryManagementSystem {
class BookNode{
	String title;
	String author;
	String genre;
	int bookId;
	boolean available;
	BookNode prev;
	BookNode next;
	
	BookNode(String title,String author,String genre,int bookId,boolean available){
		this.title=title;
		this.author=author;
		this.genre=genre;
		this.bookId=bookId;
		this.available=available;
		this.prev=null;
		this.next=null;
		
	}
}

	BookNode head=null;
	BookNode tail=null;
	int count=0;
	
	void addAtBeginning(String title,String author,String genre,int id,boolean available) {
		BookNode newNode=new BookNode(title,author,genre,id,available);
		if(head==null) {
			head=tail=newNode;
			
		}else {
			newNode.next=head;
			head.prev=newNode;
			head=newNode;
		}
		count++;
	}
	void addAtEnd(String title, String author, String genre, int
			id, boolean available) {
        BookNode newNode = new BookNode(title, author, genre, id, available);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        count++;
    }

    // Add at Specific Position
    void addAtPosition(int pos, String title, String author, String genre, int id, boolean available) {
        if (pos <= 1) {
            addAtBeginning(title, author, genre, id, available);
            return;
        }

        BookNode temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            addAtEnd(title, author, genre, id, available);
        } else {
            BookNode newNode = new BookNode(title, author, genre, id, available);
            newNode.next = temp.next;
            newNode.prev = temp;
            temp.next.prev = newNode;
            temp.next = newNode;
            count++;
        }
    }

    // Remove by Book ID
    void removeById(int id) {
        BookNode temp = head;

        while (temp != null) {
            if (temp.bookId == id) {

                if (temp == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                } else if (temp == tail) {
                    tail = tail.prev;
                    if (tail != null) tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }

                count--;
                System.out.println("Book removed successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    // Search by Title
    void searchByTitle(String title) {
        BookNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                displayBook(temp);
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("Book not found.");
    }

    // Search by Author
    void searchByAuthor(String author) {
        BookNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                displayBook(temp);
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("Book not found.");
    }

    // Update Availability
    void updateAvailability(int id, boolean status) {
        BookNode temp = head;

        while (temp != null) {
            if (temp.bookId == id) {
                temp.available = status;
                System.out.println("Availability updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    // Display Forward
    void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        BookNode temp = head;
        while (temp != null) {
            displayBook(temp);
            temp = temp.next;
        }
    }

    // Display Reverse
    void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }

        BookNode temp = tail;
        while (temp != null) {
            displayBook(temp);
            temp = temp.prev;
        }
    }

    // Display Book
    void displayBook(BookNode b) {
        System.out.println("ID: " + b.bookId +
                ", Title: " + b.title +
                ", Author: " + b.author +
                ", Genre: " + b.genre +
                ", Available: " + (b.available ? "Yes" : "No"));
    }

    // Count Books
    void totalBooks() {
        System.out.println("Total books in library: " + count);
    }

    // Main Method
    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();

        lms.addAtEnd("Java Basics", "James Gosling", "Programming", 101, true);
        lms.addAtEnd("Clean Code", "Robert Martin", "Software", 102, false);
        lms.addAtBeginning("Data Structures", "Mark Allen", "CS", 100, true);

        lms.displayForward();
        lms.totalBooks();

        lms.searchByAuthor("Robert Martin");
        lms.updateAvailability(102, true);

        lms.displayReverse();

        lms.removeById(101);
        lms.totalBooks();
    }
}

