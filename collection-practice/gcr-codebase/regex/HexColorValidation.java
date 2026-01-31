package regex;
import java.util.regex.*;
public class HexColorValidation {
public static void main(String[] args) {
	String[] colors= {"#FFA500",
            "#ff4500",
            "#123",
            "123456",
            "#GGHHII"};
	// Regex explanation:
    // ^#           -> starts with #
    // [0-9A-Fa-f]  -> hexadecimal characters
    // {6}          -> exactly 6 characters
    // $            -> end of string
	String regex="^#[0-9A-Fa-f]{6}$";
	Pattern pattern=Pattern.compile(regex);
	for(String color:colors) {
		Matcher matcher=pattern.matcher(color);
		if(matcher.matches()) {
			System.out.println(color + " Valid");
		}else {
			System.out.println(color + " Invalid");
		}
	}
}
}
