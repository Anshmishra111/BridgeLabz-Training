package built_in_functions;
import java.time.ZonedDateTime;
import java.time.ZoneId;
public class TimeZoneDisplay {
	
	    public static void main(String[] args) {
	        ZonedDateTime gmt = ZonedDateTime.now(ZoneId.of("GMT"));
	        ZonedDateTime ist = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
	        ZonedDateTime pst = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

	        System.out.println("GMT Time: " + gmt);
	        System.out.println("IST Time: " + ist);
	        System.out.println("PST Time: " + pst);
	    }
	}


