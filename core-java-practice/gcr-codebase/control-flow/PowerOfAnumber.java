import java.util.Scanner;

public class PowerOfAnumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int power = sc.nextInt();
        if (number < 0 || power < 0) {
            System.out.println("Please enter positive integers only");
        } else {

            int result = 1;
            for (int i = 1; i <= power; i++) {
                result = result * number;
            }
            System.out.println(number + " raised to the power " + power + " is " + result);
        }

        sc.close();
    }
}

        