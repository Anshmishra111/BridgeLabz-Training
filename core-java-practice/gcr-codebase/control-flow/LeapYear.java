import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        if (year < 1582) {
            System.out.println("Year should be greater than or equal to 1582");
        } else {
            if (year % 400 == 0) {
                System.out.println(year + " is a Leap Year (Multiple if-else)");
            } else if (year % 100 == 0) {
                System.out.println(year + " is not a Leap Year (Multiple if-else)");
            } else if (year % 4 == 0) {
                System.out.println(year + " is a Leap Year (Multiple if-else)");
            } else {
                System.out.println(year + " is not a Leap Year (Multiple if-else)");
            }
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                System.out.println(year + " is a Leap Year (Single if)");
            } else {
                System.out.println(year + " is not a Leap Year (Single if)");
            }
        }

        sc.close();
    }
}

        