import java.util.Scanner;
public class PerimeterOfSqr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double perim;
        System.out.print("Enter the perimeter of the square: ");
        perim=sc.nextDouble();
        double side = perim / 4;
        System.out.println("The length of the side is " + side +" whose perimeter is " + perim);
        sc.close();
    }
}
