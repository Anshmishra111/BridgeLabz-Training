package built_in_functions;
import java.util.Scanner;
public class BasicCalculator {
	    public static double add(double a, double b) { return a + b; }
	    public static double subtract(double a, double b) { return a - b; }
	    public static double multiply(double a, double b) { return a * b; }
	    public static double divide(double a, double b) { return b != 0 ? a / b : Double.NaN; }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Choose operation (+, -, *, /): ");
	        char op = scanner.next().charAt(0);

	        System.out.print("Enter two numbers: ");
	        double a = scanner.nextDouble(), b = scanner.nextDouble();

	        switch (op) {
	            case '+': System.out.println("Result: " + add(a, b)); break;
	            case '-': System.out.println("Result: " + subtract(a, b)); break;
	            case '*': System.out.println("Result: " + multiply(a, b)); break;
	            case '/': System.out.println("Result: " + divide(a, b)); break;
	            default: System.out.println("Invalid operation!");
	        }
	    }
	}


