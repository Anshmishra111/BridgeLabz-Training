package method;
import java.util.Scanner;
public class MonthlyCalendarDisplay {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int month = sc.nextInt();
	        int year = sc.nextInt();

	        printCalendar(month, year);
	        sc.close();
	    }

	    static void printCalendar(int month, int year) {
	        String[] months = {
	            "", "January", "February", "March", "April", "May", "June",
	            "July", "August", "September", "October", "November", "December"
	        };

	        int[] daysInMonth = {
	            0, 31, 28, 31, 30, 31, 30,
	            31, 31, 30, 31, 30, 31
	        };

	        if (isLeapYear(year))
	            daysInMonth[2] = 29;

	        int firstDay = getFirstDay(month, year);
	        int totalDays = daysInMonth[month];

	        System.out.println("\n  " + months[month] + " " + year);
	        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

	        for (int i = 0; i < firstDay; i++)
	            System.out.print("    ");

	        for (int day = 1; day <= totalDays; day++) {
	            System.out.printf("%3d ", day);
	            if ((day + firstDay) % 7 == 0 || day == totalDays)
	                System.out.println();
	        }
	    }

	    static boolean isLeapYear(int year) {
	        if (year % 4 != 0) return false;
	        else if (year % 100 != 0) return true;
	        else return year % 400 == 0;
	    }

	    static int getFirstDay(int m, int y) {
	        int y0 = y - (14 - m) / 12;
	        int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
	        int m0 = m + 12 * ((14 - m) / 12) - 2;
	        int d0 = (1 + x + (31 * m0) / 12) % 7;
	        return d0;
	    }
	}


