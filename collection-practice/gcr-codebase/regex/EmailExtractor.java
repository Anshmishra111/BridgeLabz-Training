package regex;
import java.util.regex.*;
public class EmailExtractor {
public static void main(String[] args) {
	String text="Contact us at support@example.com and info@company.org";
	 // Regex explanation:
    // [a-zA-Z0-9._%+-]+  -> username part
    // @                  -> @ symbol
    // [a-zA-Z0-9.-]+     -> domain name
    // \\.                -> dot
    // [a-zA-Z]{2,}       -> top-level domain
	String regex="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    Pattern pattern=Pattern.compile(regex);
    Matcher matcher=pattern.matcher(text);
    while(matcher.find()) {
    	System.out.println(matcher.group());
    }
}
}
