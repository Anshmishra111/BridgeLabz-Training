package lambda_expression;
import java.util.Arrays;
import java.util.List;
public class EmployeeNameUpperCase {
public static void main(String[] args) {
	List<String> employeeNames= Arrays.asList("Ansh",
            "Rahul",
            "Priya",
            "Amit");
	employeeNames.stream()
	             .map(String::toUpperCase)
	             .forEach(System.out::println);
}
}
