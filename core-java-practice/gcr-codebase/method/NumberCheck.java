package method;
import java.util.Scanner;
public class NumberCheck {
	static int checkNumber(int num) {
		if(num>0) {
			return 1;
		}
		else if(num<0) {
			return -1;
		}
		else {
			return 0;
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter an integer : ");
		int number=sc.nextInt();
		int result =checkNumber(number);
		if(result<0) {
			System.out.println("Number is Negative");
			
		}else if(result>0) {
			System.out.println("Number is positive");
		}
		else {
			System.out.println("Number is Zero");
		}
		sc.close();
	}

}
