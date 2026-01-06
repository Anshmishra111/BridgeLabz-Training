package scenariobased;

public class MathUtility {

	    // Method to calculate factorial
	    static long factorial(int n) {
	        if (n < 0) {
	            System.out.println("Factorial not defined for negative numbers");
	            return -1;
	        }
	        long fact = 1;
	        for (int i = 1; i <= n; i++) {
	            fact *= i;
	        }
	        return fact;
	    }

	    // Method to check if a number is prime
	    static boolean isPrime(int n) {
	        if (n <= 1) {
	            return false;
	        }
	        for (int i = 2; i <= Math.sqrt(n); i++) {
	            if (n % i == 0) {
	                return false;
	            }
	        }
	        return true;
	    }

	    // Method to find GCD of two numbers
	    static int gcd(int a, int b) {
	        if (a < 0) a = -a;
	        if (b < 0) b = -b;

	        while (b != 0) {
	            int temp = b;
	            b = a % b;
	            a = temp;
	        }
	        return a;
	    }

	    // Method to find nth Fibonacci number
	    static int fibonacci(int n) {
	        if (n < 0) {
	            System.out.println("Fibonacci not defined for negative numbers");
	            return -1;
	        }
	        if (n == 0) return 0;
	        if (n == 1) return 1;

	        int a = 0, b = 1;
	        for (int i = 2; i <= n; i++) {
	            int c = a + b;
	            a = b;
	            b = c;
	        }
	        return b;
	    }

	    // Testing all methods
	    public static void main(String[] args) {

	        // Factorial
	        System.out.println("Factorial of 5: " + factorial(5));
	        System.out.println("Factorial of 0: " + factorial(0));
	        System.out.println("Factorial of -3: " + factorial(-3));

	        // Prime Check
	        System.out.println("Is 7 prime? " + isPrime(7));
	        System.out.println("Is 1 prime? " + isPrime(1));
	        System.out.println("Is -5 prime? " + isPrime(-5));

	        // GCD
	        System.out.println("GCD of 24 and 18: " + gcd(24, 18));
	        System.out.println("GCD of -12 and 6: " + gcd(-12, 6));

	        // Fibonacci
	        System.out.println("Fibonacci of 7: " + fibonacci(7));
	        System.out.println("Fibonacci of 0: " + fibonacci(0));
	        System.out.println("Fibonacci of -4: " + fibonacci(-4));
	    }
	}


