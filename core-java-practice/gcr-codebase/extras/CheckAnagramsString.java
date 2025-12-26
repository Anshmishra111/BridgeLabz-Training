package string;
import java.util.Scanner;
public class CheckAnagramsString {
	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);
	        System.out.print("Enter first string: ");
	        String str1 = sc.nextLine();

	        System.out.print("Enter second string: ");
	        String str2 = sc.nextLine();
	        str1 = str1.toLowerCase();
	        str2 = str2.toLowerCase();
	        if (str1.length() != str2.length()) {
	            System.out.println("The strings are NOT anagrams.");
	            return;
	        }
	        int[] freq = new int[256];
	        for (int i = 0; i < str1.length(); i++) {
	            freq[str1.charAt(i)]++;
	        }
	        for (int i = 0; i < str2.length(); i++) {
	            freq[str2.charAt(i)]--;
	        }
	        for (int i = 0; i < 256; i++) {
	            if (freq[i] != 0) {
	                System.out.println("The strings are NOT anagrams.");
	                sc.close();
	                return;
	            }
	        }
	        System.out.println("The strings are ANAGRAMS.");

	        sc.close();
	    }
	}



