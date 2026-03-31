package method;
import java.util.Scanner;

public class LineDistanceAndEquation {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        int x1 = sc.nextInt();
	        int y1 = sc.nextInt();
	        int x2 = sc.nextInt();
	        int y2 = sc.nextInt();

	        double distance = calculateDistance(x1, y1, x2, y2);
	        double[] line = findLineEquation(x1, y1, x2, y2);

	        System.out.println("Euclidean Distance: " + distance);
	        System.out.println("Line Equation: y = " + line[0] + "x + " + line[1]);
	        sc.close();
	    }

	    static double calculateDistance(int x1, int y1, int x2, int y2) {
	        return Math.round(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) * 100.0) / 100.0;
	    }

	    static double[] findLineEquation(int x1, int y1, int x2, int y2) {
	        double m = (double) (y2 - y1) / (x2 - x1);
	        double b = y1 - m * x1;
	        m = Math.round(m * 100.0) / 100.0;
	        b = Math.round(b * 100.0) / 100.0;
	        return new double[] { m, b };
	    }
	}


