package scenariobased;
interface IPayable {
 double calculateBill();
}

//Parent Class
class Patient {
 private int id;
 private String name;
 private int age;

 // Constructor
 public Patient(int id, String name, int age) {
     this.id = id;
     this.name = name;
     this.age = age;
 }

 // Encapsulation (Getters)
 public int getId() {
     return id;
 }

 public String getName() {
     return name;
 }

 public int getAge() {
     return age;
 }

 // Polymorphism
 public void displayInfo() {
     System.out.println("Patient ID : " + id);
     System.out.println("Name       : " + name);
     System.out.println("Age        : " + age);
 }
}

//Child Class - InPatient
class InPatient extends Patient implements IPayable {
 private int days;
 private double dailyCharge;

 public InPatient(int id, String name, int age, int days, double dailyCharge) {
     super(id, name, age);
     this.days = days;
     this.dailyCharge = dailyCharge;
 }

 public double calculateBill() {
     return days * dailyCharge;
 }

 public void displayInfo() {
     super.displayInfo();
     System.out.println("Patient Type : InPatient");
     System.out.println("Total Bill   : ₹" + calculateBill());
 }
}

//Child Class - OutPatient
class OutPatient extends Patient implements IPayable {
 private double consultationFee;

 public OutPatient(int id, String name, int age, double fee) {
     super(id, name, age);
     this.consultationFee = fee;
 }

 public double calculateBill() {
     return consultationFee;
 }

 public void displayInfo() {
     super.displayInfo();
     System.out.println("Patient Type : OutPatient");
     System.out.println("Total Bill   : ₹" + calculateBill());
 }
}

//Main Class
public class HospitalPatientManagementSystem {
 public static void main(String[] args) {

     // Create Patients (CRUD - Create)
     Patient p1 = new InPatient(1, "Rahul", 32, 4, 2000);
     Patient p2 = new OutPatient(2, "Anita", 26, 500);

     // Read (Display)
     System.out.println("---- Patient Details ----");
     p1.displayInfo();

     System.out.println();
     p2.displayInfo();
 }
}


