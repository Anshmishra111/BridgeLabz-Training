package stream_api;
import java.util.*;
import java.util.stream.*;
public class EventWelcomeMessage {
	public static void main(String[] args) {
		List<String> attendes=Arrays.asList("Amit", "Rahul", "Neha", "Priya");
		attendes.stream()
		.forEach(name -> System.out.println("Welcome " + name + "!"));
	}

}
