package collections;
import java.util.*;
class Patient{
	String name;
	int severity;
	Patient(String name,int severity){
		this.name=name;
		this.severity=severity;
	}
}
public class HospitalTriageSystem {

public static void main(String[] args) {
	PriorityQueue<Patient> queue=new PriorityQueue<>((a, b) -> b.severity - a.severity);
	queue.add(new Patient("John", 3));
    queue.add(new Patient("Alice", 5));
    queue.add(new Patient("Bob", 2));

    // Treat patients
    System.out.println("Treatment Order:");
    while (!queue.isEmpty()) {
        Patient p = queue.poll();
        System.out.println(p.name);
    }
}
}
