package constructors;

public class UniversityManagementSystem {
	    public static void main(String[] args) {
	        Student s1 = new Student(101, "Ansh", 8.5);
	        s1.displayStudentDetails();

	        PostgraduateStudent pg =
	            new PostgraduateStudent(201, "Rohit", 8.8, "CS");
	        pg.displayPostgraduateDetails();
	    }
	}

	class Student {
	    public int rollNumber;
	    protected String name;
	    private double cgpa;

	    Student(int rollNumber, String name, double cgpa) {
	        this.rollNumber = rollNumber;
	        this.name = name;
	        this.cgpa = cgpa;
	    }

	    public double getCGPA() {
	        return cgpa;
	    }

	    public void setCGPA(double cgpa) {
	        this.cgpa = cgpa;
	    }

	    void displayStudentDetails() {
	        System.out.println("Roll Number: " + rollNumber);
	        System.out.println("Name: " + name);
	        System.out.println("CGPA: " + cgpa);
	    }
	}

	class PostgraduateStudent extends Student {

	    String specialization;

	    PostgraduateStudent(int r, String n, double c, String s) {
	        super(r, n, c);
	        specialization = s;
	    }

	    void displayPostgraduateDetails() {
	        System.out.println("Roll Number: " + rollNumber);
	        System.out.println("Name: " + name);
	        System.out.println("Specialization: " + specialization);
	        System.out.println("CGPA: " + getCGPA());
	    }
	}


