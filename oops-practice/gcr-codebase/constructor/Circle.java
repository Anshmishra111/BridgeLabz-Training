package constructors;

public class Circle {
	    // Attribute
	    double radius;

	    // Default constructor (sets default radius)
	    Circle() {
	        this(1.0); // constructor chaining
	    }

	    // Parameterized constructor
	    Circle(double radius) {
	        this.radius = radius;
	    }

	    // Method to display radius
	    void displayRadius() {
	        System.out.println("Radius of the circle: " + radius);
	    }

	    public static void main(String[] args) {

	        // Using default constructor
	        Circle circle1 = new Circle();
	        circle1.displayRadius();

	        // Using parameterized constructor
	        Circle circle2 = new Circle(2.5);
	        circle2.displayRadius();
	    }
	}


