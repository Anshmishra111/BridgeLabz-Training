package built_in_functions;
import java.util.Scanner;
public class PalindromeChecker {
	    public static boolean isPalindrome(String str) {
	        str = str.replaceAll("\s+", "").toLowerCase();
	        return str.equals(new StringBuilder(str).reverse().toString());
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter a string: ");
	        String input = scanner.nextLine();
	        System.out.println(input + (isPalindrome(input) ? " is a palindrome." : " is not a palindrome."));
	    }
	}


