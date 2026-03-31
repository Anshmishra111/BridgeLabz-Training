package built_in_functions;
import java.util.Scanner;
public class PrimeChecker {
	    public static boolean isPrime(int n) {
	        if (n <= 1) return false;
	        for (int i = 2; i <= Math.sqrt(n); i++)
	            if (n % i == 0) return false;
	        return true;
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter a number: ");
	        int number = scanner.nextInt();
	        System.out.println(number + (isPrime(number) ? " is a prime number." : " is not a prime number."));
	    }
	}

