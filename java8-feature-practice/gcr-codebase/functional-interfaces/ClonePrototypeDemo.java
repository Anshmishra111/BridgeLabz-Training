package functional_interfaces;

//Prototype class
class Product implements Cloneable {
 int id;
 String name;

 Product(int id, String name) {
     this.id = id;
     this.name = name;
 }

 public Product clone() {
     try {
         return (Product) super.clone();
     } catch (CloneNotSupportedException e) {
         return null;
     }
 }
}

//Main class
public class ClonePrototypeDemo {
 public static void main(String[] args) {

     Product original = new Product(101, "Laptop");

     Product copy = original.clone();

     System.out.println("Original: " + original.id + " " + original.name);
     System.out.println("Cloned  : " + copy.id + " " + copy.name);
 }
}
