package method;
import java.util.Scanner;
public class CollinearPointsCheck {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        int x1 = sc.nextInt();
	        int y1 = sc.nextInt();
	        int x2 = sc.nextInt();
	        int y2 = sc.nextInt();
	        int x3 = sc.nextInt();
	        int y3 = sc.nextInt();

	        boolean collinearSlope = isCollinearBySlope(x1, y1, x2, y2, x3, y3);
	        boolean collinearArea = isCollinearByArea(x1, y1, x2, y2, x3, y3);

	        System.out.println("Using Slope Method: " + (collinearSlope ? "Collinear" : "Not Collinear"));
	        System.out.println("Using Area Method: " + (collinearArea ? "Collinear" : "Not Collinear"));
	        sc.close();
	    }

	    static boolean isCollinearBySlope(int x1, int y1, int x2, int y2, int x3, int y3) {
	        int slopeAB_num = y2 - y1;
	        int slopeAB_den = x2 - x1;

	        int slopeAC_num = y3 - y1;
	        int slopeAC_den = x3 - x1;

	        return slopeAB_num * slopeAC_den == slopeAC_num * slopeAB_den;
	    }

	    static boolean isCollinearByArea(int x1, int y1, int x2, int y2, int x3, int y3) {
	        int area = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2);
	        return area == 0;
	    }
	}

