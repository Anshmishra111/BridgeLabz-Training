package functional_interfaces;

import java.util.function.Function;

public class StringLengthChecker {
    public static void main(String[] args) {

        int limit = 10;

        Function<String, Integer> lengthFunction =
            str -> str.length();

        String message = "Hello Functional Interface";

        int length = lengthFunction.apply(message);

        if (length > limit) {
            System.out.println("Message exceeds character limit");
        } else {
            System.out.println("Message within character limit");
        }
    }
}
