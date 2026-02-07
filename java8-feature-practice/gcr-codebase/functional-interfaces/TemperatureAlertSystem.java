package functional_interfaces;
import java.util.function.Predicate;
public class TemperatureAlertSystem {
	public static void main(String[] args) {
		double threshold=37.5;
		Predicate<Double> highTemp=temp -> temp > threshold;
		double[] readings={36.5, 37.2, 38.1, 39.0};
		for(double temp:readings) {
			if(highTemp.test(temp)) {
				System.out.println("ALERT!High temperatire: " + temp);
			}
		}
	}

}
