import java.util.Scanner;
public class FeetToYardAndMiles{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double distInFeet;
        System.out.print("Enter distance in feet: ");
        distInFeet =sc.nextDouble();
        double yards = distInFeet/3;
        double miles = yards/1760;
        System.out.println("The distance in yards is " + yards + " and the distance in miles is " + miles);
    }
}
