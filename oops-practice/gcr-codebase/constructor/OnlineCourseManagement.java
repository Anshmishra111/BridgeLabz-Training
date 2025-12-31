package constructors;

public class OnlineCourseManagement {
	    // Instance variables (unique for each course)
	    String courseName;
	    int duration;       // in months
	    double fee;

	    // Class variable (shared among all courses)
	    static String instituteName = "Tech Academy";

	    // Constructor
	    OnlineCourseManagement(String courseName, int duration, double fee) {
	        this.courseName = courseName;
	        this.duration = duration;
	        this.fee = fee;
	    }

	    // Instance method
	    void displayCourseDetails() {
	        System.out.println("Institute Name: " + instituteName);
	        System.out.println("Course Name   : " + courseName);
	        System.out.println("Duration      : " + duration + " months");
	        System.out.println("Fee           : " + fee);
	        System.out.println("-------------------------------");
	    }

	    // Class method to update institute name
	    static void updateInstituteName(String newName) {
	        instituteName = newName;
	    }

	    public static void main(String[] args) {

	        // Create courses
	    	OnlineCourseManagement c1 = new OnlineCourseManagement("Java Full Stack", 6, 45000.0);
	    	OnlineCourseManagement c2 = new OnlineCourseManagement("Python Data Science", 8, 60000.0);

	        // Display course details (before update)
	        c1.displayCourseDetails();
	        c2.displayCourseDetails();

	        // Update institute name for all courses
	        OnlineCourseManagement.updateInstituteName("Global Tech Institute");

	        // Display course details (after update)
	        c1.displayCourseDetails();
	        c2.displayCourseDetails();
	    }
	}


