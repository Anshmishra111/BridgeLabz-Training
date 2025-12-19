import java.util.Scanner;
public class QuoAndRem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1, num2;
        System.out.print("Enter first number: ");
        num1 = sc.nextInt();
        System.out.print("Enter second number: ");
        num2 = sc.nextInt();
        int quo = num1 / num2;
        int rem= num1 % num2;
        System.out.println("The Quotient is " + quo + " and Reminder is " + rem + " of two number " + num1 + " and " + num2);
        sc.close();
    }
}
