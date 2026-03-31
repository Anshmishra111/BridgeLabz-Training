package regex;
import java.util.regex.*;
public class LicensePlateValidation {
public static void main(String[] args) {
	String[] plates= { "AB1234",
            "A12345"};
	// Regex explanation:
    // ^[A-Z]{2}  -> exactly two uppercase letters
    // [0-9]{4}   -> exactly four digits
    // $          -> end of string
	String regex="^[A-Z]{2}[0-9]{4}$";
	Pattern pattern=Pattern.compile(regex);
	for(String plate:plates) {
		Matcher matcher=pattern.matcher(plate);
		if(matcher.matches()) {
			System.out.println(plate + " valid");
		}else {
			System.out.println(plate + " Invalid");
		}
	}
}
}
