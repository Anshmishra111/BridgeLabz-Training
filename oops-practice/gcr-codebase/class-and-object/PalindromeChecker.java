package classAndObject;

public class PalindromeChecker {
	    // Attribute
	    String text;

	    // Constructor
	    PalindromeChecker(String text) {
	        this.text = text;
	    }

	    // Method to check if the text is a palindrome
	    boolean isPalindrome() {

	        // Remove spaces and convert to lowercase
	        String cleanedText = text.replaceAll("\\s+", "").toLowerCase();

	        int start = 0;
	        int end = cleanedText.length() - 1;

	        while (start < end) {
	            if (cleanedText.charAt(start) != cleanedText.charAt(end)) {
	                return false;
	            }
	            start++;
	            end--;
	        }
	        return true;
	    }

	    // Method to display result
	    void displayResult() {
	        if (isPalindrome()) {
	            System.out.println(text + " is palindrome");
	        } else {
	            System.out.println(text + " is not Palindrome");
	        }
	    }

	    public static void main(String[] args) {

	        // First example
	        PalindromeChecker p1 = new PalindromeChecker(
	                "A man a plan a canal Panama"
	        );
	        p1.displayResult();

	        // Second example
	        PalindromeChecker p2 = new PalindromeChecker("Hello");
	        p2.displayResult();
	    }
	}


