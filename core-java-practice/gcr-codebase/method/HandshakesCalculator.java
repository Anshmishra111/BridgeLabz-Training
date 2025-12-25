package method;
import java.util.Scanner;
public class HandshakesCalculator {
	static int calculateHandshakes(int numberOfStudents) {
		return (numberOfStudents*(numberOfStudents-1))/2;
	}
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		System.out.print("Enter no of Students : ");
		int numberOfStudents=sc.nextInt();
		int handshakes=calculateHandshakes(numberOfStudents);
		System.out.println("The maximum no. of possible  handshakes among " + numberOfStudents + " students are " + handshakes);
		sc.close();
	}

}
