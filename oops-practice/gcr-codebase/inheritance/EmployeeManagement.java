package inheritance;

public class EmployeeManagement {
	    String name;
	    int id;
	    double salary;

	    EmployeeManagement(String name, int id, double salary) {
	        this.name = name;
	        this.id = id;
	        this.salary = salary;
	    }

	    void displayDetails() {
	        System.out.println("Name: " + name + ", ID: " + id + ", Salary: " + salary);
	    }
	}

	class Manager extends EmployeeManagement {
	    int teamSize;

	    Manager(String name, int id, double salary, int teamSize) {
	        super(name, id, salary);
	        this.teamSize = teamSize;
	    }

	    @Override
	    void displayDetails() {
	        super.displayDetails();
	        System.out.println("Team Size: " + teamSize);
	    }
	}

	class Developer extends EmployeeManagement {
	    String programmingLanguage;

	    Developer(String name, int id, double salary, String language) {
	        super(name, id, salary);
	        this.programmingLanguage = language;
	    }

	    @Override
	    void displayDetails() {
	        super.displayDetails();
	        System.out.println("Language: " + programmingLanguage);
	    }
	}

	class Intern extends EmployeeManagement {
	    int durationMonths;

	    Intern(String name, int id, double salary, int duration) {
	        super(name, id, salary);
	        this.durationMonths = duration;
	    }

	    @Override
	    void displayDetails() {
	        super.displayDetails();
	        System.out.println("Internship Duration: " + durationMonths + " months");
	    }
	}

