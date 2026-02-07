package stream_api;
import java.util.*;
import java.util.stream.*;
public class IotSensorReadings {
	public static void main(String[] args) {
		List<Integer> readings=Arrays.asList(45,60,30,80,55,90);
		int threshold=50;
		readings.stream()
		.filter(r -> r > threshold)
		.forEach(r -> 
		System.out.println("High reading: " + r));
		
	}
}
