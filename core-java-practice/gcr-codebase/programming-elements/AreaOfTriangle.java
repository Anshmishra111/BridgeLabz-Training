import java.util.Scanner;
public class AreaOfTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double b, h;
        System.out.print("Enter base in inches: ");
        b = sc.nextDouble();
        System.out.print("Enter height in inches: ");
        h = sc.nextDouble();
        double aInches = 0.5 * b * h;
        double aCm = aInches * 6.4516;
        System.out.println("The area of the triangle is " + aInches + " square inches and " + aCm + " square centimeters");
        sc.close();
    }
}