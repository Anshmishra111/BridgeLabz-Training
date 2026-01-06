package encapsulation_polymorphism;

	abstract class Employee {
	    private String employeeId;
	    private String name;
	    private double baseSalary;

	    public Employee(String employeeId, String name, double baseSalary) {
	        this.employeeId = employeeId;
	        this.name = name;
	        this.baseSalary = baseSalary;
	    }

	    public abstract double calculateSalary();

	    public void displayDetails() {
	        System.out.println("ID: " + employeeId + ", Name: " + name + ", Salary: " + calculateSalary());
	    }

	    public double getBaseSalary() {
	        return baseSalary;
	    }
	}

	interface Department {
	    void assignDepartment(String dept);
	    String getDepartmentDetails();
	}

	class FullTimeEmployee extends Employee implements Department {
	    private String department;

	    public FullTimeEmployee(String id, String name, double salary) {
	        super(id, name, salary);
	    }

	    public double calculateSalary() {
	        return getBaseSalary();
	    }

	    public void assignDepartment(String dept) {
	        this.department = dept;
	    }

	    public String getDepartmentDetails() {
	        return "Department: " + department;
	    }
	}

	class PartTimeEmployee extends Employee implements Department {
	    private int hoursWorked;
	    private String department;

	    public PartTimeEmployee(String id, String name, double hourlyRate, int hoursWorked) {
	        super(id, name, hourlyRate);
	        this.hoursWorked = hoursWorked;
	    }

	    public double calculateSalary() {
	        return getBaseSalary() * hoursWorked;
	    }

	    public void assignDepartment(String dept) {
	        this.department = dept;
	    }

	    public String getDepartmentDetails() {
	        return "Department: " + department;
	    }
	}

	public class EmployeeManagementSystem {
	    public static void main(String[] args) {
	        Employee e1 = new FullTimeEmployee("E101", "Alice", 50000);
	        Employee e2 = new PartTimeEmployee("E102", "Bob", 200, 30);

	        e1.displayDetails();
	        e2.displayDetails();
	    }
	}

