package classAndObject;

public class EmployeeDetails {
	// Attributes of Employee
	    String name;
	    int id;
	    double salary;

	    // Constructor to initialize employee details
	    public EmployeeDetails(String name, int id, double salary) {
	        this.name = name;
	        this.id = id;
	        this.salary = salary;
	    }

	    // Method to display employee details
	    public void displayDetails() {
	        System.out.println("Employee Name: " + name);
	        System.out.println("Employee ID: " + id);
	        System.out.println("Employee Salary: " + salary);
	    }

	    // Main method to create object and call method
	    public static void main(String[] args) {
	        EmployeeDetails emp = new EmployeeDetails("Rohan", 1, 500000);
	        emp.displayDetails();
	    }
	}

