package functional_interfaces;

//Common interface
interface RentalVehicle {
 void rent();
 void returnVehicle();
}

//Car implementation
class Car implements RentalVehicle {
 public void rent() {
     System.out.println("Car rented successfully");
 }

 public void returnVehicle() {
     System.out.println("Car returned successfully");
 }
}

//Bike implementation
class Bike implements RentalVehicle {
 public void rent() {
     System.out.println("Bike rented successfully");
 }

 public void returnVehicle() {
     System.out.println("Bike returned successfully");
 }
}

//Bus implementation
class Bus implements RentalVehicle {
 public void rent() {
     System.out.println("Bus rented successfully");
 }

 public void returnVehicle() {
     System.out.println("Bus returned successfully");
 }
}

//Main controller
public class VehicleRentalSystem {

 public static void main(String[] args) {

     RentalVehicle car = new Car();
     RentalVehicle bike = new Bike();
     RentalVehicle bus = new Bus();

     car.rent();
     bike.rent();
     bus.rent();

     bus.returnVehicle();
     bike.returnVehicle();
     car.returnVehicle();
 }
}
