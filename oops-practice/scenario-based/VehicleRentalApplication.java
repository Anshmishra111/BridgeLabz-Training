package scenariobased;
//Interface - Abstraction
interface IRentable {
 double calculateRent(int days);
}

//Base Class
class Vehicle {
 protected int vehicleId;
 protected String brand;
 protected double ratePerDay;

 public Vehicle(int vehicleId, String brand, double ratePerDay) {
     this.vehicleId = vehicleId;
     this.brand = brand;
     this.ratePerDay = ratePerDay;
 }

 public void displayInfo() {
     System.out.println("Vehicle ID : " + vehicleId);
     System.out.println("Brand      : " + brand);
     System.out.println("Rate/Day   : ₹" + ratePerDay);
 }
}

//Bike Class
class Bike extends Vehicle implements IRentable {

 public Bike(int id, String brand, double rate) {
     super(id, brand, rate);
 }

 public double calculateRent(int days) {
     return ratePerDay * days;
 }

 public void displayInfo() {
     super.displayInfo();
     System.out.println("Type       : Bike");
 }
}

//Car Class
class Car extends Vehicle implements IRentable {

 public Car(int id, String brand, double rate) {
     super(id, brand, rate);
 }

 public double calculateRent(int days) {
     return (ratePerDay * days) + 500; // service charge
 }

 public void displayInfo() {
     super.displayInfo();
     System.out.println("Type       : Car");
 }
}

//Truck Class
class Truck extends Vehicle implements IRentable {

 public Truck(int id, String brand, double rate) {
     super(id, brand, rate);
 }

 public double calculateRent(int days) {
     return (ratePerDay * days) + 1000; // heavy vehicle charge
 }

 public void displayInfo() {
     super.displayInfo();
     System.out.println("Type       : Truck");
 }
}

//Customer Class
class Customer {
 private int customerId;
 private String name;

 public Customer(int customerId, String name) {
     this.customerId = customerId;
     this.name = name;
 }

 public void displayCustomer() {
     System.out.println("Customer ID: " + customerId);
     System.out.println("Name       : " + name);
 }
}

//Main Class
public class VehicleRentalApplication {
 public static void main(String[] args) {

     // Create Objects (CRUD - Create)
     IRentable bike = new Bike(101, "Yamaha", 300);
     IRentable car = new Car(102, "Honda", 1200);
     IRentable truck = new Truck(103, "Tata", 2500);

     Customer customer = new Customer(1, "Ansh");

     // Read (Display)
     System.out.println("---- Customer Details ----");
     customer.displayCustomer();

     System.out.println("\n---- Rental Details ----");
     ((Bike) bike).displayInfo();
     System.out.println("Rent for 3 days: ₹" + bike.calculateRent(3));

     System.out.println();
     ((Car) car).displayInfo();
     System.out.println("Rent for 2 days: ₹" + car.calculateRent(2));

     System.out.println();
     ((Truck) truck).displayInfo();
     System.out.println("Rent for 1 day: ₹" + truck.calculateRent(1));
 }
}

