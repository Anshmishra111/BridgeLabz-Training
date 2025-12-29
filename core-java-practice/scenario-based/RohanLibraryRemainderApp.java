package scenario_based;
import java.util.*;
public class RohanLibraryRemainderApp {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	int totalFine=0;
	int finePerDay=5;
	for(int i=1;i<=5;i++) {
		System.out.println("\"\\nBook ");
		System.out.print("Enter due date (day number): ");
		int dueDate=sc.nextInt();
		System.out.print("Enter return date (day number): ");
		int returnDate=sc.nextInt();
				if(returnDate>dueDate) {
					int lateDays=returnDate-dueDate;
					int fine=lateDays*finePerDay;
					System.out.println("Late by " + lateDays + " days. Fine = ₹" + fine);
					totalFine+=fine;
				}
				else {
					System.out.println("Returned on time. No fine.");	
				}
	}
				
				System.out.println("\\nTotal fine for all books = ₹ " + totalFine);
	}
}

