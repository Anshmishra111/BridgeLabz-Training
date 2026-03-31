package functional_interfaces;

//Common payment interface
interface Payment {
 void pay(double amount);
}

//UPI payment implementation
class UPI implements Payment {
 public void pay(double amount) {
     System.out.println("Paid ₹" + amount + " using UPI");
 }
}

//Credit Card payment implementation
class CreditCard implements Payment {
 public void pay(double amount) {
     System.out.println("Paid ₹" + amount + " using Credit Card");
 }
}

//Wallet payment implementation
class Wallet implements Payment {
 public void pay(double amount) {
     System.out.println("Paid ₹" + amount + " using Wallet");
 }
}

//Main class
public class DigitalPaymentSystem {

 public static void main(String[] args) {

     Payment upi = new UPI();
     Payment card = new CreditCard();
     Payment wallet = new Wallet();

     upi.pay(1500);
     card.pay(3200);
     wallet.pay(500);
 }
}
