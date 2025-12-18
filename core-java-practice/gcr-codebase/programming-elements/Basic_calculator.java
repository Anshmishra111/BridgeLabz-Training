import java.util.Scanner;
public class BasicCalc{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double num1, num2;
        System.out.print("Enter first number: ");
        num1 = sc.nextDouble();
        System.out.print("Enter second number: ");
        num2= sc.nextDouble();
        double add = num1 + num2;
        double sub = num1 - num2;
        double multip = num1 * num2;
        double div = num1 / num2;
        System.out.println("The addition, subtraction, multiplication and division value of 2 numbers " +num1 + " and " + num2 + " is " + add + ", " + sub + ", " + multip+ ", and " + div);
    }
}
