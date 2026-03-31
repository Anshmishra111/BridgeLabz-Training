package built_in_functions;
import java.util.Scanner;
public class FibonacciGenerator {
	    public static void generateFibonacci(int terms) {
	        int a = 0, b = 1;
	        for (int i = 0; i < terms; i++) {
	            System.out.print(a + " ");
	            int next = a + b;
	            a = b;
	            b = next;
	        }
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter number of terms: ");
	        int terms = scanner.nextInt();
	        generateFibonacci(terms);
	    }
	}

