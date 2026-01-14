package scenariobased;
class RoomNotAvailableException extends Exception{
	public RoomNotAvailableException(String message) {
		super(message);
}
}
interface PricingStrategy{
	double calculatePrice(double basePrice,int days);
	}
class NormalPricing implements PricingStrategy{
	public double calculatePrice(double basePrice,int days) {
		return basePrice*days;
	}
}
class SeasonalPricing implements PricingStrategy{
	public double calculatePrice(double basePrice,int days) {
		return(basePrice*days)*1.2;
	}
}
class Room{
	protected int roomNumber;
	protected double pricePerDay;
	protected boolean available=true;
	
	public Room(int roomNumber,double pricePerPay) {
		this.roomNumber=roomNumber;
		this.pricePerDay=pricePerDay;
	}
	public void checkAvailability() throws RoomNotAvailableException{
		if(!available) {
			throw new RoomNotAvailableException("Room " + roomNumber + " is not available!");
		}
	}
	public void checkIn() {
		available=false;
		
	}
	public void checkOut() {
		available=true;
	}
}
class StandardRoom extends Room{
	public StandardRoom(int roomNumber) {
		super(roomNumber,2000);
	}
}
class DeluxeRoom extends Room{
	public DeluxeRoom(int roomNumber) {
		super(roomNumber,4000);
	}
}
class Guest{
	private int guestId;
	private String name;
	
	public Guest(int guestId,String name) {
		this.guestId=guestId;
		this.name=name;
	}
	public String getName(){
		return name;
	}
}
class Reservation{
	private int reservationId;
	private Room room;
	private Guest guest;
	private int days;
	
	public Reservation(int reservationId,Room room,Guest guest,int days) {
		this.reservationId=reservationId;
		this.room=room;
		this.guest=guest;
		this.days=days;
	}
	public double generateBill(PricingStrategy strategy) {
		return strategy.calculatePrice(room.pricePerDay, days);
	}
	public void displayReservation() {
		System.out.println("Reservation ID : " + reservationId);
        System.out.println("Guest          : " + guest.getName());
        System.out.println("Room Number    : " + room.roomNumber);
        System.out.println("Days Stayed    : " + days);
	}
}

public class HotelReservationSystem {
	public static void main(String[] args) {
		Room standardRoom=new StandardRoom(101);
		Room DeluxeRoom=new DeluxeRoom(201);
		
		Guest guest=new Guest(1,"Ansh");
		
		try {
			standardRoom.checkAvailability();
			standardRoom.checkIn();
			
			Reservation reservation=new Reservation(5001,standardRoom,guest,3);
			PricingStrategy pricing=new SeasonalPricing();
			double bill=reservation.generateBill(pricing);
			System.out.println("---- Reservation Details ----");
            reservation.displayReservation();
            System.out.println("Total Bill    : ₹" + bill);

            // Check-out
            standardRoom.checkOut();
            System.out.println("\nCheck-out completed.");

        } catch (RoomNotAvailableException e) {
            System.out.println(e.getMessage());
			
		}
	}
}
