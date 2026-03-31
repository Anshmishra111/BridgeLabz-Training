package constructors;

public class EmployeeRecords {
	    public static void main(String[] args) {

	        // Create Employee object
	        Employee emp = new Employee(101, "IT", 45000.0);
	        emp.displayEmployeeDetails();

	        // Modify salary using public method
	        emp.updateSalary(55000.0);

	        // Create Manager object
	        Manager manager = new Manager(201, "HR", 80000.0, "Senior Manager");
	        manager.displayManagerDetails();
	    }
	}

	// -------------------- Employee Class --------------------
	class Employee {

	    public int employeeID;        // public
	    protected String department;  // protected
	    private double salary;         // private

	    Employee(int employeeID, String department, double salary) {
	        this.employeeID = employeeID;
	        this.department = department;
	        this.salary = salary;
	    }

	    // Public method to modify salary
	    public void updateSalary(double newSalary) {
	        salary = newSalary;
	        System.out.println("Updated Salary: " + salary);
	    }

	    // Public getter for salary
	    public double getSalary() {
	        return salary;
	    }

	    void displayEmployeeDetails() {
	        System.out.println("Employee ID : " + employeeID);
	        System.out.println("Department  : " + department);
	        System.out.println("Salary      : " + salary);
	        System.out.println("---------------------------");
	    }
	}

	// -------------------- Manager Class --------------------
	class Manager extends Employee {

	    String designation;

	    Manager(int empID, String dept, double sal, String designation) {
	        super(empID, dept, sal);
	        this.designation = designation;
	    }

	    // Demonstrates access to public & protected members
	    void displayManagerDetails() {
	        System.out.println("Manager ID   : " + employeeID); // public
	        System.out.println("Department   : " + department); // protected
	        System.out.println("Designation  : " + designation);
	        System.out.println("Salary       : " + getSalary()); // private via getter
	        System.out.println("---------------------------");
	    }
	}


