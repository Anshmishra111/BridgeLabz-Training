package scenario_based;

public class LineComparison {
	    private int x1, y1, x2, y2;

	    // Constructor
	    public LineComparison(int x1, int y1, int x2, int y2) {
	        this.x1 = x1;
	        this.y1 = y1;
	        this.x2 = x2;
	        this.y2 = y2;
	    }

	    // UC1: Calculate length of the line
	    public double calculateLength() {
	        return Math.sqrt(
	                Math.pow((x2 - x1), 2) +
	                Math.pow((y2 - y1), 2)
	        );
	    }

	    public static void main(String[] args) {

	        System.out.println("Welcome to Line Comparison Computation Program");

	        // Create two lines
	        LineComparison line1 = new LineComparison(1, 2, 4, 6);
	        LineComparison line2 = new LineComparison(2, 3, 6, 7);

	        // UC1: Calculate lengths
	        Double length1 = line1.calculateLength();
	        Double length2 = line2.calculateLength();

	        System.out.println("Length of Line 1: " + length1);
	        System.out.println("Length of Line 2: " + length2);

	        // UC2: Check equality using equals()
	        if (length1.equals(length2)) {
	            System.out.println("Both lines are equal");
	        } else {
	            System.out.println("Lines are not equal");
	        }

	        // UC3: Compare lines using compareTo()
	        int comparison = length1.compareTo(length2);

	        if (comparison == 0) {
	            System.out.println("Line 1 is equal to Line 2");
	        } else if (comparison > 0) {
	            System.out.println("Line 1 is greater than Line 2");
	        } else {
	            System.out.println("Line 1 is less than Line 2");
	        }
	    }
	}



