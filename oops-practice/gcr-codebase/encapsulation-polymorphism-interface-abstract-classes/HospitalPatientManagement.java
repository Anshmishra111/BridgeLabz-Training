package encapsulation_polymorphism;
	abstract class Patient {
	    private String patientId;
	    private String name;
	    private int age;

	    public Patient(String id, String name, int age) {
	        this.patientId = id;
	        this.name = name;
	        this.age = age;
	    }

	    public abstract double calculateBill();

	    public void getPatientDetails() {
	        System.out.println("Patient: " + name + ", Age: " + age);
	    }
	}

	interface MedicalRecord {
	    void addRecord(String record);
	    void viewRecords();
	}

	class InPatient extends Patient implements MedicalRecord {
	    private double roomCharges;

	    public InPatient(String id, String name, int age, double roomCharges) {
	        super(id, name, age);
	        this.roomCharges = roomCharges;
	    }

	    public double calculateBill() {
	        return roomCharges + 500; // Base treatment
	    }

	    public void addRecord(String record) {
	        System.out.println("Record Added: " + record);
	    }

	    public void viewRecords() {
	        System.out.println("Viewing records...");
	    }
	}

	public class HospitalPatientManagement {
	    public static void main(String[] args) {
	        Patient patient = new InPatient("P101", "Rahul", 30, 3000);
	        patient.getPatientDetails();
	        System.out.println("Total Bill: ₹" + patient.calculateBill());
	    }
	}

