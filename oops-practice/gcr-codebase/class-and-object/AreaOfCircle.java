package classAndObject;

public class AreaOfCircle {
	    // Attribute
	    double radius;

	    // Constructor
	    AreaOfCircle(double radius) {
	        this.radius = radius;
	    }

	    // Method to calculate area
	    void calculateArea() {
	        double area = Math.PI * radius * radius;
	        System.out.printf("Area of circle: %.4f%n", area);
	    }

	    // Method to calculate circumference
	    void calculateCircumference() {
	        double circumference = 2 * Math.PI * radius;
	        System.out.printf("Circumference of circle: %.4f%n", circumference);
	    }

	    public static void main(String[] args) {

	        // Create Circle object with radius = 2.5
	    	AreaOfCircle circle = new AreaOfCircle(2.5);

	        // Display results
	        circle.calculateArea();
	        circle.calculateCircumference();
	    }
	}
