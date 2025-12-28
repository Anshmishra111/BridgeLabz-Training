package built_in_functions;
import java.util.Scanner;
public class TemperatureConverter {
	    public static double toCelsius(double f) {
	        return (f - 32) * 5 / 9;
	    }

	    public static double toFahrenheit(double c) {
	        return (c * 9 / 5) + 32;
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Convert to (C/F): ");
	        String choice = scanner.next().toUpperCase();

	        System.out.print("Enter temperature: ");
	        double temp = scanner.nextDouble();

	        if (choice.equals("C")) {
	            System.out.println("In Celsius: " + toCelsius(temp));
	        } else {
	            System.out.println("In Fahrenheit: " + toFahrenheit(temp));
	        }
	    }
	}


