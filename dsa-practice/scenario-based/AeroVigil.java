package scenario_based;
import java.util.*;
class InvalidFlightException extends Exception{
	public InvalidFlightException(String msg) {
		super(msg);
	}
}
class FlightUtil{
	public boolean validateFlightNumber(String flightNumber)
            throws InvalidFlightException {
		if(!flightNumber.matches("FL-[1-9][0-9]{3}")) {
			throw new InvalidFlightException("the flight number" + flightNumber + "is invalid");
		}
		return true;
}
	public boolean validateFlightName(String flightName)
            throws InvalidFlightException {
		if(!(flightName.equals("SpiceJet")||flightName.equals("Vistara")||flightName.equals("Indigo")||flightName.equals("Air arabia"))) {
			throw new InvalidFlightException("The flight name " + flightName + "is invalid");
		}
		return true;
	}
	public boolean validatePassengerCount(int count, String flightName)
            throws InvalidFlightException {
		int max=0;
		if(flightName.equals("SpiceJet")) max=396;
		else if(flightName.equals("Vistara")) max=615;
		else if(flightName.equals("Indigo")) max=230;
		else if(flightName.equals("Air Arabia")) max=130;
		
		if(count<=0||count>max) {
			throw new InvalidFlightException("The passenger count" + count + "is invalid");
		}
		return true;
}
	 public double calculateFuelToFillTank(String flightName, double fuel)
	            throws InvalidFlightException {
		 double capacity=0;
		 if(flightName.equals("SpiceJet")) capacity=200000;
		 else if(flightName.equals("Vistara")) capacity=300000;
		 else if(flightName.equals("Indigo")) capacity=250000;
		 else if(flightName.equals("Air Arabia")) capacity=150000;
		 
		 if(fuel<0||fuel>capacity) {
			 throw new InvalidFlightException("Invalid fuel level for " + flightName);
		 }
		 return capacity-fuel;
}
}
public class AeroVigil {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	FlightUtil util=new FlightUtil();
	System.out.println("Enter flight details");
	String input=sc.nextLine();
	try {
		String[] data=input.split(":");
		util.validateFlightNumber(data[0]);
		util.validateFlightName(data[1]);
		util.validatePassengerCount(Integer.parseInt(data[2]),data[1]);
		
		double fuel=util.calculateFuelToFillTank(data[1],Double.parseDouble(data[3]));
		
		System.out.println("Fuel required to fill the tank: " + fuel + "litres");
		
	}catch(InvalidFlightException e) {
		System.out.println(e.getMessage());
	}
}
}
