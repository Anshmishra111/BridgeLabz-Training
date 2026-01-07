package scenariobased;
import java.util.ArrayList;
import java.util.List;


// ---------- Custom Exception ----------
class NoDriverAvailableException extends Exception {
    public NoDriverAvailableException(String message) {
        super(message);
    }
}

//fare calculator interface
interface  FareCalculator{
	double calculateFare(double distance);
}
//---Normal Pricing
class NormalFareCalculator implements FareCalculator{
	public double calculateFare(double distance) {
		return distance*10;
	}
}
class PeakFareCalculator implements FareCalculator{
	public double calculateFare(double distance) {
		return distance*15;
	}
}
//--- user class
class User{
	String name;
	User(String name){
		this.name=name;
	}
}
// driver class

class Driver{
	String name;
	boolean isAvailable;
	Driver(String name){
		this.name=name;
		this.isAvailable=true;
		
	}
}
// ride class
class Ride{
	User user;
	Driver driver;
	double distance;
	double fare;
	
	Ride(User user,Driver driver,double distance,FareCalculator calculator){
		this.user=user;
		this.driver=driver;
		this.distance=distance;
		this.fare=calculator.calculateFare(distance);
	}
	void displayRide() {
        System.out.println(
                "User: " + user.name +
                ", Driver: " + driver.name +
                ", Distance: " + distance +
                " km, Fare: ₹" + fare
        );
    }
	
}
//---------- Ride Management System ----------
public class RideBookingSystem{
	static List<Driver> drivers=new ArrayList<>();
	static List<Ride> ridehistory=new ArrayList<>();
	  
	
	//Assign driver available
	
	static Driver assignDriver() throws NoDriverAvailableException{
		for(Driver d:drivers) {
			if(d.isAvailable) {
				d.isAvailable=false;
				return d;
			}
		}
		throw  new NoDriverAvailableException("No drivers available currently");
	}
	// Book a ride
	
	static void bookRide(User user,double distance,FareCalculator calculator) {
		try { 
			Driver driver=assignDriver();
			Ride ride =new Ride(user,driver,distance,calculator);
			ridehistory.add(ride);
			System.out.println("Ride booked Successfully");
			ride.displayRide();
		}catch (NoDriverAvailableException e){
			System.out.println("Error : " + e.getMessage());
			
			
		}
		
	}
	//show ride history
	static void showRideHistory() {
		System.out.println("\n---ride history");
		for(Ride r:ridehistory) {
			r.displayRide();
		}
		
	}
	public static void main(String[] args) {
		//Add drivers
		drivers.add(new Driver("Ramesh"));
		drivers.add(new Driver("Suresh"));
		User user1=new User("Ansh");
		User user2=new User("Riya");
		FareCalculator normalFare=new NormalFareCalculator();
		FareCalculator peakFare=new PeakFareCalculator();
		
		//Book Rides
		
		bookRide(user1,8,normalFare);
		bookRide(user2,5,peakFare);
		bookRide(user1,3,normalFare);
		
		//Show ride history
		showRideHistory();
		
	}
	
}
