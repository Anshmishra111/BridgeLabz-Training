package regex;

import java.util.regex.*;

public class IPValidation {
    public static void main(String[] args) {

        String[] ips = {
                "192.168.1.1",
                "255.255.255.255",
                "256.100.50.0",
                "192.168.1",
                "abc.def.gha.bcd"
        };

        // Regex for numbers 0-255
        String regex =
                "^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.){3}" +
                "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])$";

        Pattern pattern = Pattern.compile(regex);

        for (String ip : ips) {

            Matcher matcher = pattern.matcher(ip);

            if (matcher.matches()) {
                System.out.println(ip + " → Valid");
            } else {
                System.out.println(ip + " → Invalid");
            }
        }
    }
}
