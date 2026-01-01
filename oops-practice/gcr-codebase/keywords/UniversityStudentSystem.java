package keywords;

public class UniversityStudentSystem {

	    public static void main(String[] args) {

	        // Create Student objects
	        Student s1 = new Student(101, "Hemashree", "A");
	        Student s2 = new Student(102, "Sharmila", "B");

	        // Display total students
	        Student.displayTotalStudents();

	        // Display details using instanceof check
	        if (s1 instanceof Student) {
	            s1.displayStudentDetails();
	        }

	        if (s2 instanceof Student) {
	            s2.displayStudentDetails();
	        }

	        // Update grade after checking instanceof
	        if (s2 instanceof Student) {
	            s2.updateGrade("A");
	            s2.displayStudentDetails();
	        }
	    }
	}

	// -------------------- Student Class --------------------
	class Student {

	    // Static variables
	    static String universityName = "Global University";
	    static int totalStudents = 0;

	    // Instance variables
	    final int rollNumber;   // final (cannot be changed)
	    String name;
	    String grade;

	    // Constructor using 'this'
	    Student(int rollNumber, String name, String grade) {
	        this.rollNumber = rollNumber;
	        this.name = name;
	        this.grade = grade;
	        totalStudents++;
	    }

	    // Static method
	    static void displayTotalStudents() {
	        System.out.println("Total Students Enrolled: " + totalStudents);
	    }

	    // Method to update grade
	    void updateGrade(String newGrade) {
	        this.grade = newGrade;
	        System.out.println("Grade updated to: " + grade);
	    }

	    // Method to display student details
	    void displayStudentDetails() {
	        System.out.println("University Name: " + universityName);
	        System.out.println("Roll Number: " + rollNumber);
	        System.out.println("Name: " + name);
	        System.out.println("Grade: " + grade);
	    }
	}


