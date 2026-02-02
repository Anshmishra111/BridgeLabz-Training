package annotations;

//Parent class
class Animal {

 public void makeSound() {
     System.out.println("Animal makes a sound");
 }
}

//Child class
class Dog extends Animal {

 @Override
 public void makeSound() {
     System.out.println("Dog barks");
 }
}

//Main class
public class OverrideDemo {

 public static void main(String[] args) {

     Animal dog = new Dog(); // polymorphism
     dog.makeSound();
 }
}
