import java.util.Scanner;
public class FeetIntoInches {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double heightCm;
        System.out.print("Enter your height in centimeters: ");
        heightCm = sc.nextDouble();
        double totInches = heightCm/2.54;
        int feet = (int) (totInches / 12);
        double inches = totInches % 12;
        System.out.println("Your Height in cm is " + heightCm + " while in feet is " + feet + " and inches is " + inches);
        sc.close();
    }
}
