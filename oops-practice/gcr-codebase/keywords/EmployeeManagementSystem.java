package keywords;

public class EmployeeManagementSystem {
	    public static void main(String[] args) {

	        // Create Employee objects
	        Employee emp1 = new Employee(101, "Thamarai", "Software Engineer");
	        Employee emp2 = new Employee(102, "Rohan", "Project Manager");

	        // Display total employees
	        Employee.displayTotalEmployees();

	        // Check instanceof before displaying details
	        if (emp1 instanceof Employee) {
	            emp1.displayEmployeeDetails();
	        }

	        if (emp2 instanceof Employee) {
	            emp2.displayEmployeeDetails();
	        }
	    }
	}

	// -------------------- Employee Class --------------------
	class Employee {

	    // Static variable (shared by all employees)
	    static String companyName = "Tech Solutions Inc.";
	    static int totalEmployees = 0;

	    // Instance variables
	    final int id;          // final (cannot be changed)
	    String name;
	    String designation;

	    // Constructor using 'this'
	    Employee(int id, String name, String designation) {
	        this.id = id;
	        this.name = name;
	        this.designation = designation;
	        totalEmployees++;
	    }

	    // Static method
	    static void displayTotalEmployees() {
	        System.out.println("Total Employees: " + totalEmployees);
	    }

	    // Method to display employee details
	    void displayEmployeeDetails() {
	        System.out.println("Company Name: " + companyName);
	        System.out.println("Employee ID: " + id);
	        System.out.println("Name: " + name);
	        System.out.println("Designation: " + designation);
	    }
	}


