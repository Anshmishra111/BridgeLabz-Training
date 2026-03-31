package regex;
import java.util.regex.Matcher;
import java.util.regex.Matcher.*;
import java.util.regex.Pattern;
public class CapitalizeWordExtractor {
public static void main(String[] args) {
	String text="The Eiffel Tower is in Paris and the Statue of Liberty is in New York.";
	// Regex explanation:
    // \b            -> word boundary
    // [A-Z]         -> capital letter
    // [a-z]*        -> remaining lowercase letters
    // \b            -> word boundary
	String regex="\\b[A-Z][a-z]*\\b";
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(text);
	boolean first=true;
	while(matcher.find()) {
		
		if(!first) {
			System.out.print(", ");
		}
		System.out.print(matcher.group());
		first=false;
		}
	}
}

