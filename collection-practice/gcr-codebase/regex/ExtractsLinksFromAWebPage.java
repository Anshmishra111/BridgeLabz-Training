package regex;
import java.util.regex.*;

public class ExtractsLinksFromAWebPage {
	    public static void main(String[] args) {

	        String text = "Visit https://www.google.com and http://example.org for more info.";

	        // Regex explanation:
	        // https?://  -> matches http or https
	        // [^\\s]+    -> matches everything until a space
	        String regex = "https?://[^\\s]+";

	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(text);

	        boolean first = true;

	        while (matcher.find()) {
	            if (!first) {
	                System.out.print(", ");
	            }
	            System.out.print(matcher.group());
	            first = false;
	        }
	    }
	}


