package scenariobased;

import java.util.Scanner;

//---------- ABSTRACT CLASS ----------
abstract class GoodsTransport {

 protected String transportId;
 protected String transportDate;
 protected int transportRating;

 public GoodsTransport(String transportId, String transportDate, int transportRating) {
     this.transportId = transportId;
     this.transportDate = transportDate;
     this.transportRating = transportRating;
 }

 public abstract String vehicleSelection();
 public abstract float calculateTotalCharge();
}

//---------- BRICK TRANSPORT ----------
class BrickTransport extends GoodsTransport {

 private float brickSize;
 private int brickQuantity;
 private float brickPrice;

 public BrickTransport(String id, String date, int rating,
                       float size, int qty, float price) {
     super(id, date, rating);
     brickSize = size;
     brickQuantity = qty;
     brickPrice = price;
 }

 public String vehicleSelection() {
     if (brickQuantity < 300) return "Truck";
     else if (brickQuantity <= 500) return "Lorry";
     else return "MonsterLorry";
 }

 public float calculateTotalCharge() {
     float price = brickPrice * brickQuantity;
     float tax = price * 0.3f;

     float discount = (transportRating == 5) ? price * 0.20f :
                      (transportRating >= 3) ? price * 0.10f : 0;

     float vehicleCost =
             vehicleSelection().equalsIgnoreCase("Truck") ? 1000 :
             vehicleSelection().equalsIgnoreCase("Lorry") ? 1700 : 3000;

     return price + tax + vehicleCost - discount;
 }
}

//---------- TIMBER TRANSPORT ----------
class TimberTransport extends GoodsTransport {

 private float length, radius, price;
 private String type;

 public TimberTransport(String id, String date, int rating,
                        float len, float rad, String type, float price) {
     super(id, date, rating);
     this.length = len;
     this.radius = rad;
     this.type = type;
     this.price = price;
 }

 public String vehicleSelection() {
     float area = 2 * 3.147f * radius * length;
     if (area < 250) return "Truck";
     else if (area <= 400) return "Lorry";
     else return "MonsterLorry";
 }

 public float calculateTotalCharge() {
     float volume = 3.147f * radius * radius * length;
     float rate = type.equalsIgnoreCase("Premium") ? 0.25f : 0.15f;

     float cost = volume * price * rate;
     float tax = cost * 0.3f;

     float discount = (transportRating == 5) ? cost * 0.20f :
                      (transportRating >= 3) ? cost * 0.10f : 0;

     float vehicleCost =
             vehicleSelection().equalsIgnoreCase("Truck") ? 1000 :
             vehicleSelection().equalsIgnoreCase("Lorry") ? 1700 : 3000;

     return cost + tax + vehicleCost - discount;
 }
}

//---------- UTILITY ----------
class Utility {

 public static boolean validateTransportId(String id) {
     if (!id.matches("RTS\\d{3}[A-Z]")) {
         System.out.println("Transport id " + id + " is invalid");
         System.out.println("Please provide a valid record");
         return false;
     }
     return true;
 }

 public static GoodsTransport parseDetails(String input) {

     String[] d = input.split(":");

     if (!validateTransportId(d[0])) return null;

     if (d[3].equalsIgnoreCase("BrickTransport")) {
         return new BrickTransport(
                 d[0], d[1], Integer.parseInt(d[2]),
                 Float.parseFloat(d[4]),
                 Integer.parseInt(d[5]),
                 Float.parseFloat(d[6])
         );
     } else {
         return new TimberTransport(
                 d[0], d[1], Integer.parseInt(d[2]),
                 Float.parseFloat(d[4]),
                 Float.parseFloat(d[5]),
                 d[6],
                 Float.parseFloat(d[7])
         );
     }
 }
}

//---------- MAIN CLASS ----------
public class FutureLogistics {

 public static void main(String[] args) {

     Scanner sc = new Scanner(System.in);
     System.out.println("Enter the Goods Transport details");
     String input = sc.nextLine();

     GoodsTransport gt = Utility.parseDetails(input);
     if (gt == null) return;

     System.out.println("Transporter id : " + gt.transportId);
     System.out.println("Date of transport : " + gt.transportDate);
     System.out.println("Rating of the transport : " + gt.transportRating);
     System.out.println("Vehicle for transport : " + gt.vehicleSelection());
     System.out.println("Total charge : " + gt.calculateTotalCharge());
 }
}
