package keywords;

public class HospitalManagementSystem {

	    public static void main(String[] args) {

	        // Create Patient objects
	        Patient p1 = new Patient("P001", "Lathika", 30, "Flu");
	        Patient p2 = new Patient("P002", "Lidiya", 45, "Fracture");

	        // Display total patients admitted
	        Patient.getTotalPatients();

	        // Check instanceof before displaying details
	        if (p1 instanceof Patient) {
	            p1.displayPatientDetails();
	        }

	        if (p2 instanceof Patient) {
	            p2.displayPatientDetails();
	        }
	    }
	}

	// -------------------- Patient Class --------------------
	class Patient {

	    // Static variables
	    static String hospitalName = "City Hospital";
	    static int totalPatients = 0;

	    // Instance variables
	    final String patientID;   // final (cannot be changed)
	    String name;
	    int age;
	    String ailment;

	    // Constructor using 'this'
	    Patient(String patientID, String name, int age, String ailment) {
	        this.patientID = patientID;
	        this.name = name;
	        this.age = age;
	        this.ailment = ailment;
	        totalPatients++;
	    }

	    // Static method to get total patients
	    static void getTotalPatients() {
	        System.out.println("Total Patients Admitted: " + totalPatients);
	    }

	    // Method to display patient details
	    void displayPatientDetails() {
	        System.out.println("Hospital Name: " + hospitalName);
	        System.out.println("Patient ID: " + patientID);
	        System.out.println("Name: " + name);
	        System.out.println("Age: " + age);
	        System.out.println("Ailment: " + ailment);
	    }
	}


