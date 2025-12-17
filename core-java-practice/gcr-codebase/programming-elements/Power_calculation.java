import java.util.Scanner;
class PowCal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter base: ");

        double base = sc.nextDouble();

        System.out.print("Enter expo: ");
        
        double exponent = sc.nextDouble();

        double result = Math.pow(base, exponent);
        System.out.println("Result = " + result);
    }
}
