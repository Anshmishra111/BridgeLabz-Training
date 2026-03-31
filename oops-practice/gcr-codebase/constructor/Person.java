package constructors;

public class Person {
	    // Attributes
	    String name;
	    int age;

	    // Parameterized constructor
	    Person(String name, int age) {
	        this.name = name;
	        this.age = age;
	    }

	    // Copy constructor
	    Person(Person other) {
	        this.name = other.name;
	        this.age = other.age;
	    }

	    // Method to display person details
	    void displayDetails() {
	        System.out.println("Name: " + name);
	        System.out.println("Age: " + age);
	        System.out.println("--------------------");
	    }

	    public static void main(String[] args) {

	        // Original person
	        Person p1 = new Person("Ravi", 25);
	        p1.displayDetails();

	        // Clone using copy constructor
	        Person p2 = new Person(p1);
	        p2.displayDetails();
	    }
	}


