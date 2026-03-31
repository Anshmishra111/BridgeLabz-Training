package regex;
import java.util.regex.*;
public class ValidateUsrname {
	public static void main(String[] args) {
		String[] usernames= {"user_123",
                "123user",
                "us"};
        String regex = "^[A-Za-z][A-Za-z0-9_]{4,14}$";
    Pattern pattern=Pattern.compile(regex);
    for(String username:usernames) {
    	Matcher matcher=pattern.matcher(username);
    	if(matcher.matches()) {
    		System.out.println(username + " valid");
    		
    	}else {
    		System.out.println(username + " Invalid");
    	}
    }
	}

}
