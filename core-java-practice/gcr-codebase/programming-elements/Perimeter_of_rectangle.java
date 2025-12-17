import java.util.Scanner;
class PerOfRect {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter l: ");

        double length = sc.nextDouble();

        System.out.print("Enter w: ");

        double width = sc.nextDouble();

        double perimeter = 2 * (length + width);
        System.out.println("Perimeter of Rectangle = " + perimeter);
    }
}
