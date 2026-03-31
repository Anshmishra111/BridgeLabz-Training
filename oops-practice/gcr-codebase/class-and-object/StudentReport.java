package classAndObject;

public class StudentReport {
	    // Attributes
	    String name;
	    String rollNumber;
	    double mark1;
	    double mark2;
	    double mark3;

	    // Constructor
	    StudentReport(String name, String rollNumber, double mark1, double mark2, double mark3) {
	        this.name = name;
	        this.rollNumber = rollNumber;
	        this.mark1 = mark1;
	        this.mark2 = mark2;
	        this.mark3 = mark3;
	    }

	    // Method to calculate grade based on average marks
	    char calculateGrade() {
	        double average = (mark1 + mark2 + mark3) / 3;

	        if (average >= 75) {
	            return 'A';
	        } else if (average >= 65) {
	            return 'B';
	        } else if (average >= 50) {
	            return 'C';
	        } else {
	            return 'D';
	        }
	    }

	    // Method to display student details and grade
	    void displayStudentReport() {
	        System.out.println("Student Name: " + name);
	        System.out.println("Student RollNumber: " + rollNumber);
	        System.out.println("Student Marks:");
	        System.out.println("Mark1: " + mark1);
	        System.out.println("Mark2: " + mark2);
	        System.out.println("Mark3: " + mark3);
	        System.out.println("Grade " + calculateGrade());
	    }

	    public static void main(String[] args) {

	        // First student
	    	StudentReport student1 = new StudentReport(
	                "Thamarai", "ECE001", 80.0, 70.0, 75.0
	        );
	        student1.displayStudentReport();

	        // Second student
	        StudentReport student2 = new StudentReport(
	                "Kannan", "CSC002", 60.0, 65.0, 50.0
	        );
	        student2.displayStudentReport();
	    }
	}



