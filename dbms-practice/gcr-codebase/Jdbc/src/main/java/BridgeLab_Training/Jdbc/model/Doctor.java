package BridgeLab_Training.Jdbc.model;

public class Doctor {

    private int doctorId;
    private String name;
    private String contact;
    private double consultationFee;
    private int specialtyId;
    private boolean isActive;

    public Doctor() {}

    public Doctor(String name, String contact, double consultationFee, int specialtyId) {
        this.name = name;
        this.contact = contact;
        this.consultationFee = consultationFee;
        this.specialtyId = specialtyId;
        this.isActive = true;
    }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }

    public int getSpecialtyId() { return specialtyId; }
    public void setSpecialtyId(int specialtyId) { this.specialtyId = specialtyId; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}