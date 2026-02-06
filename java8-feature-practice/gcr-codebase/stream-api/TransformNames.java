package stream_api;
import java.util.*;
import java.util.stream.*;

public class TransformNames {
	public static void main(String[] args) {
		List<String> names=Arrays.asList("rahul", "amit", "neha", "priya", "suman");
		names.stream()
		.map(name -> name.toUpperCase())
		.sorted()
		.forEach(System.out::println);
	}

}
