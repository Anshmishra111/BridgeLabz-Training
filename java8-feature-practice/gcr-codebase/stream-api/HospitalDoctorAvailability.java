package stream_api;
import java.util.*;
import java.util.stream.*;
class  Doctor{
	String name;
	String speciality;
	boolean availableOnWeekend;
	
	Doctor(String name,String speciality,boolean availableOnWeekend){
		this.name=name;
		this.speciality=speciality;
		this.availableOnWeekend=availableOnWeekend;
	}
}

public class HospitalDoctorAvailability {
	public static void main(String[] args) {
		List<Doctor> doctors=Arrays.asList(new Doctor("Dr A","Cardiology", true),
				new Doctor("Dr B", "Neurology", false),
	            new Doctor("Dr C", "Orthopedic", true),
	            new Doctor("Dr D", "Dermatology", true),
	            new Doctor("Dr E", "Cardiology", false));
		doctors.stream()
		.filter(d ->d.availableOnWeekend)
		.sorted(Comparator.comparing(d -> d.speciality))
		.forEach(d -> System.out.println(d.name + " - " + d.speciality));
	}

}
