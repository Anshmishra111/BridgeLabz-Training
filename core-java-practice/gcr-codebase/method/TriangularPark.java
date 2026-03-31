package method;
import java.util.Scanner;
public class TriangularPark {
	static double calculateRounds(double side1, double side2, double side3) {
		double perimeter=side1+side2+side3;
		double totalDistance=5000;
		return totalDistance/perimeter;
	}
	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter side 1 : ");
	double side1=sc.nextDouble();
	System.out.print("Enter side 2 : ");
	double side2=sc.nextDouble();
	System.out.print("Enter side 3 : ");
	double side3=sc.nextDouble();
	double numberOfRounds=calculateRounds(side1,side2,side3);
	System.out.println("Number of Rounds :  " + numberOfRounds);

}
}
