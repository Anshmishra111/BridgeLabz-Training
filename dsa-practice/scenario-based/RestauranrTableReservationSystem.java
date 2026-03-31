package scenario_based;
import java.util.*;
//custom exception
	class TableAlreadyReservedException extends Exception{
		public TableAlreadyReservedException(String message) {
			super(message);
		}
	}
	// table class 
	class Table{
		int tableNumber;
		Table(int tableNumber){
			this.tableNumber=tableNumber;
		}
	}
	//reservation class
	class Reservation{
		int tableNumber;
		String timeSlot;
		Reservation(int tableNumber, String timeSlot){
			this.tableNumber=tableNumber;
			this.timeSlot=timeSlot;
		}
	}
	public class RestauranrTableReservationSystem {
      
		//map:tableNumber
		static Map<Integer,Table> tables=new HashMap<>();
		static List<Reservation> reservations=new ArrayList<>();
		//Reserve table method
		static void reserveTable(int tableNumber,String timeSlot) throws TableAlreadyReservedException{
			for(Reservation r:reservations) {
			if(r.tableNumber==tableNumber && r.timeSlot.equalsIgnoreCase(timeSlot)) { throw new TableAlreadyReservedException("table already reserved");
				
			}
		}
			reservations.add(new Reservation(tableNumber, timeSlot));
	        System.out.println("Table " + tableNumber +
	                " reserved for " + timeSlot);
}
		//cancel reservation
		static void cancelReservation(int tableNumber, String timeSlot) {
	        reservations.removeIf(r ->
	                r.tableNumber == tableNumber &&
	                r.timeSlot.equalsIgnoreCase(timeSlot));

	        System.out.println("Reservation cancelled for Table " + tableNumber);
		}  
	        //show available trains
	        static void showAvailableTables(String timeSlot) {
	        	System.out.println("Available tables for " + timeSlot + ":");
	        	for (int tableNo : tables.keySet()) {
	                boolean booked = false;

	                for (Reservation r : reservations) {
	                    if (r.tableNumber == tableNo &&
	                        r.timeSlot.equalsIgnoreCase(timeSlot)) {
	                        booked = true;
	                        break;
	        }
}
	                if(!booked) {
	                	System.out.println("Table" + tableNo);
	                }
	        	}
	        }
	        public static void main(String[] args) {
	        	//initialize tables
	        	tables.put(1, new Table(1));
	        	tables.put(2, new Table(2));
	        	tables.put(3, new Table(3));
	        	
	        	try {
	        		reserveTable(1, "7PM-8PM");
	                reserveTable(2, "7PM-8PM");
	                reserveTable(1, "7PM-8PM"); // double booking

	        	} catch(TableAlreadyReservedException e) {
	        		System.out.println(e.getMessage());
	        	}
	        	showAvailableTables("7PM-8PM");
	        	cancelReservation(2, "7PM-8PM");

	            showAvailableTables("7PM-8PM");
	        }
		}