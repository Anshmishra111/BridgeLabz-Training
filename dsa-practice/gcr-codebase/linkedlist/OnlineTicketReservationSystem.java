package linkedlist;

public class OnlineTicketReservationSystem {
	    class TicketNode {
	        int ticketId;
	        String customerName;
	        String movieName;
	        String seatNumber;
	        String bookingTime;
	        TicketNode next;

	        TicketNode(int ticketId, String customerName, String movieName,
	                   String seatNumber, String bookingTime) {
	            this.ticketId = ticketId;
	            this.customerName = customerName;
	            this.movieName = movieName;
	            this.seatNumber = seatNumber;
	            this.bookingTime = bookingTime;
	            this.next = null;
	        }
	    }

	    TicketNode head = null;
	    TicketNode tail = null;
	    int count = 0;

	    // Add ticket at end
	    void addTicket(int id, String customer, String movie,
	                   String seat, String time) {

	        TicketNode newNode = new TicketNode(id, customer, movie, seat, time);

	        if (head == null) {
	            head = tail = newNode;
	            newNode.next = head;
	        } else {
	            tail.next = newNode;
	            newNode.next = head;
	            tail = newNode;
	        }
	        count++;
	    }

	    // Remove ticket by Ticket ID
	    void removeTicket(int id) {
	        if (head == null) {
	            System.out.println("No tickets booked.");
	            return;
	        }

	        TicketNode curr = head;
	        TicketNode prev = tail;

	        do {
	            if (curr.ticketId == id) {

	                if (curr == head && curr == tail) {
	                    head = tail = null;
	                } else {
	                    prev.next = curr.next;
	                    if (curr == head)
	                        head = curr.next;
	                    if (curr == tail)
	                        tail = prev;
	                }

	                count--;
	                System.out.println("Ticket removed successfully.");
	                return;
	            }
	            prev = curr;
	            curr = curr.next;
	        } while (curr != head);

	        System.out.println("Ticket not found.");
	    }

	    // Display all tickets
	    void displayTickets() {
	        if (head == null) {
	            System.out.println("No tickets booked.");
	            return;
	        }

	        TicketNode temp = head;
	        do {
	            displayTicket(temp);
	            temp = temp.next;
	        } while (temp != head);
	    }

	    // Search by Customer Name
	    void searchByCustomer(String name) {
	        if (head == null) {
	            System.out.println("No tickets booked.");
	            return;
	        }

	        TicketNode temp = head;
	        boolean found = false;

	        do {
	            if (temp.customerName.equalsIgnoreCase(name)) {
	                displayTicket(temp);
	                found = true;
	            }
	            temp = temp.next;
	        } while (temp != head);

	        if (!found)
	            System.out.println("No ticket found for this customer.");
	    }

	    // Search by Movie Name
	    void searchByMovie(String movie) {
	        if (head == null) {
	            System.out.println("No tickets booked.");
	            return;
	        }

	        TicketNode temp = head;
	        boolean found = false;

	        do {
	            if (temp.movieName.equalsIgnoreCase(movie)) {
	                displayTicket(temp);
	                found = true;
	            }
	            temp = temp.next;
	        } while (temp != head);

	        if (!found)
	            System.out.println("No tickets found for this movie.");
	    }

	    // Total tickets count
	    void totalTickets() {
	        System.out.println("Total booked tickets: " + count);
	    }

	    // Display one ticket
	    void displayTicket(TicketNode t) {
	        System.out.println("Ticket ID: " + t.ticketId +
	                ", Customer: " + t.customerName +
	                ", Movie: " + t.movieName +
	                ", Seat: " + t.seatNumber +
	                ", Time: " + t.bookingTime);
	    }

	    // Main Method (Demo)
	    public static void main(String[] args) {
	    	OnlineTicketReservationSystem trs = new OnlineTicketReservationSystem();

	        trs.addTicket(101, "Ansh", "Inception", "A10", "10:30 AM");
	        trs.addTicket(102, "Ravi", "Avatar", "B12", "11:00 AM");
	        trs.addTicket(103, "Neha", "Inception", "C05", "11:30 AM");

	        System.out.println("\nAll Tickets:");
	        trs.displayTickets();

	        System.out.println("\nSearch by Movie:");
	        trs.searchByMovie("Inception");

	        System.out.println("\nSearch by Customer:");
	        trs.searchByCustomer("Ravi");

	        trs.removeTicket(102);

	        System.out.println("\nAfter Removal:");
	        trs.displayTickets();

	        trs.totalTickets();
	    }
	}


