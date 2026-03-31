package linkedlist;
import java.util.Scanner;

public class MovieManagementSystem {

	    // Node class
	    class MovieNode {
	        String title;
	        String director;
	        int year;
	        double rating;
	        MovieNode next;
	        MovieNode prev;

	        MovieNode(String title, String director, int year, double rating) {
	            this.title = title;
	            this.director = director;
	            this.year = year;
	            this.rating = rating;
	            this.next = null;
	            this.prev = null;
	        }
	    }

	    MovieNode head = null;
	    MovieNode tail = null;

	    // Add at Beginning
	    void addAtBeginning(String title, String director, int year, double rating) {
	        MovieNode newNode = new MovieNode(title, director, year, rating);
	        if (head == null) {
	            head = tail = newNode;
	        } else {
	            newNode.next = head;
	            head.prev = newNode;
	            head = newNode;
	        }
	    }

	    // Add at End
	    void addAtEnd(String title, String director, int year, double rating) {
	        MovieNode newNode = new MovieNode(title, director, year, rating);
	        if (tail == null) {
	            head = tail = newNode;
	        } else {
	            tail.next = newNode;
	            newNode.prev = tail;
	            tail = newNode;
	        }
	    }

	    // Add at Specific Position
	    void addAtPosition(int pos, String title, String director, int year, double rating) {
	        if (pos == 1) {
	            addAtBeginning(title, director, year, rating);
	            return;
	        }

	        MovieNode temp = head;
	        for (int i = 1; i < pos - 1 && temp != null; i++) {
	            temp = temp.next;
	        }

	        if (temp == null || temp.next == null) {
	            addAtEnd(title, director, year, rating);
	        } else {
	            MovieNode newNode = new MovieNode(title, director, year, rating);
	            newNode.next = temp.next;
	            newNode.prev = temp;
	            temp.next.prev = newNode;
	            temp.next = newNode;
	        }
	    }

	    // Remove by Movie Title
	    void removeByTitle(String title) {
	        MovieNode temp = head;

	        while (temp != null) {
	            if (temp.title.equalsIgnoreCase(title)) {

	                if (temp == head) {
	                    head = head.next;
	                    if (head != null) head.prev = null;
	                } else if (temp == tail) {
	                    tail = tail.prev;
	                    tail.next = null;
	                } else {
	                    temp.prev.next = temp.next;
	                    temp.next.prev = temp.prev;
	                }

	                System.out.println("Movie removed successfully.");
	                return;
	            }
	            temp = temp.next;
	        }
	        System.out.println("Movie not found.");
	    }

	    // Search by Director
	    void searchByDirector(String director) {
	        MovieNode temp = head;
	        boolean found = false;

	        while (temp != null) {
	            if (temp.director.equalsIgnoreCase(director)) {
	                displayMovie(temp);
	                found = true;
	            }
	            temp = temp.next;
	        }

	        if (!found)
	            System.out.println("No movies found for this director.");
	    }

	    // Search by Rating
	    void searchByRating(double rating) {
	        MovieNode temp = head;
	        boolean found = false;

	        while (temp != null) {
	            if (temp.rating >= rating) {
	                displayMovie(temp);
	                found = true;
	            }
	            temp = temp.next;
	        }

	        if (!found)
	            System.out.println("No movies found with this rating.");
	    }

	    // Update Rating
	    void updateRating(String title, double newRating) {
	        MovieNode temp = head;

	        while (temp != null) {
	            if (temp.title.equalsIgnoreCase(title)) {
	                temp.rating = newRating;
	                System.out.println("Rating updated successfully.");
	                return;
	            }
	            temp = temp.next;
	        }
	        System.out.println("Movie not found.");
	    }

	    // Display Forward
	    void displayForward() {
	        MovieNode temp = head;
	        if (temp == null) {
	            System.out.println("No movies available.");
	            return;
	        }
	        while (temp != null) {
	            displayMovie(temp);
	            temp = temp.next;
	        }
	    }

	    // Display Reverse
	    void displayReverse() {
	        MovieNode temp = tail;
	        if (temp == null) {
	            System.out.println("No movies available.");
	            return;
	        }
	        while (temp != null) {
	            displayMovie(temp);
	            temp = temp.prev;
	        }
	    }

	    void displayMovie(MovieNode m) {
	        System.out.println("Title: " + m.title +
	                ", Director: " + m.director +
	                ", Year: " + m.year +
	                ", Rating: " + m.rating);
	    }

	    // Main Method (Menu Driven)
	    public static void main(String[] args) {
	        MovieManagementSystem mms = new MovieManagementSystem();
	        Scanner sc = new Scanner(System.in);

	        while (true) {
	            System.out.println("\n1.Add Beginning 2.Add End 3.Add Position 4.Remove 5.Search Director 6.Search Rating");
	            System.out.println("7.Display Forward 8.Display Reverse 9.Update Rating 10.Exit");
	            int ch = sc.nextInt();
	            sc.nextLine();

	            switch (ch) {
	                case 1 -> mms.addAtBeginning("Inception", "Nolan", 2010, 8.8);
	                case 2 -> mms.addAtEnd("Interstellar", "Nolan", 2014, 8.6);
	                case 3 -> mms.addAtPosition(2, "Avatar", "Cameron", 2009, 7.8);
	                case 4 -> mms.removeByTitle("Avatar");
	                case 5 -> mms.searchByDirector("Nolan");
	                case 6 -> mms.searchByRating(8.0);
	                case 7 -> mms.displayForward();
	                case 8 -> mms.displayReverse();
	                case 9 -> mms.updateRating("Inception", 9.0);
	                case 10 -> System.exit(0);
	            }
	        }
	    }
	}


