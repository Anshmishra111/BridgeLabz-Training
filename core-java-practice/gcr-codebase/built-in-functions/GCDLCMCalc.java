package built_in_functions;
import java.util.Scanner;
public class GCDLCMCalc {
	    public static int gcd(int a, int b) {
	        while (b != 0) {
	            int temp = b;
	            b = a % b;
	            a = temp;
	        }
	        return a;
	    }

	    public static int lcm(int a, int b) {
	        return a * (b / gcd(a, b));
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter two numbers: ");
	        int a = scanner.nextInt(), b = scanner.nextInt();

	        System.out.println("GCD: " + gcd(a, b));
	        System.out.println("LCM: " + lcm(a, b));
	    }
	}


