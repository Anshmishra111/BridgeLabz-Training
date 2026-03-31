package inheritance;

public class LibraryManagement {
	    String title;
	    int publicationYear;

	    LibraryManagement(String title, int year) {
	        this.title = title;
	        this.publicationYear = year;
	    }

	    void displayInfo() {
	        System.out.println("Title: " + title + ", Year: " + publicationYear);
	    }
	}

	class Author extends LibraryManagement {
	    String name;
	    String bio;

	    Author(String title, int year, String name, String bio) {
	        super(title, year);
	        this.name = name;
	        this.bio = bio;
	    }

	    @Override
	    void displayInfo() {
	        super.displayInfo();
	        System.out.println("Author: " + name + ", Bio: " + bio);
	    }
	}

