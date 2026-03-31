package lambda_expression;
import java.util.*;
import java.util.function.Predicate;
class Alert{
	String type;
	String message;
	Alert(String type,String message){
		this.type=type;
		this.message=message;
	}
	@Override
	public String toString() {
		return type + "Alert" + message;
	}
}

public class HospitalNotificationFilter {
	public static void main(String[] args) {
		List<Alert> alerts=Arrays.asList(
				new Alert("CRITICAL","Heart rate abnormal"),
		        new Alert("WARNING","High blood pressure"),
		        new Alert("INFO", "Appointment reminder"),
		        new Alert("CRITICAL", "Oxygen level low")
		        );
		Predicate<Alert> criticalFilter=alert -> alert.type.equals("CRITICAL");
		System.out.println("Showing critical Alerts:\n");
		alerts.stream()
		.filter(criticalFilter)
        .forEach(System.out::println);
		
				
				
		
	}

}
