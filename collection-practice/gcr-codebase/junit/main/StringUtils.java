package BridgeLabz_Training.Junit;

public class StringUtils {
  // check reverse
	public String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}
	//check palindrome
	public boolean isPalindrome(String str) {
		String reversed=reverse(str);
		return str.equalsIgnoreCase(reversed);
	}
	//convert to uppercase
	public String toUpperCase(String str) {
		return str.toUpperCase();
	}
}
