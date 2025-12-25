package method;
import java.util.Scanner;
public class SimpleInterestCalculator {
		static double calculateSi(double Principal,double Rate,double Time) {
			return (Principal*Rate*Time)/100;
		}
	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter Principal : ");
	double Principal=sc.nextDouble();
	System.out.println("Enter Rate : ");
	double Rate=sc.nextDouble();
	System.out.println("Enter Tme : ");
	double Time=sc.nextDouble();
	double CalculateSi=calculateSi(Principal,Rate,Time);
	System.out.println("The SI is "+ CalculateSi + " Principal " + Principal + " Rate  " + Rate + " Time " + Time );

}
}