package functional_interfaces;

//Interface with static method
interface SecurityUtils {

 static boolean isStrongPassword(String password) {
     if (password.length() < 8)
         return false;

     boolean hasDigit = false;
     boolean hasUpper = false;

     for (char ch : password.toCharArray()) {
         if (Character.isDigit(ch))
             hasDigit = true;
         if (Character.isUpperCase(ch))
             hasUpper = true;
     }

     return hasDigit && hasUpper;
 }
}

//Main class
public class PasswordValidator {
 public static void main(String[] args) {

     String password = "Insurance1";

     if (SecurityUtils.isStrongPassword(password)) {
         System.out.println("Strong Password");
     } else {
         System.out.println("Weak Password");
     }
 }
}
