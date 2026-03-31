package method;
import java.util.Scanner;
public class MatrixOperationTransDetInv {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        int n = sc.nextInt();
	        int[][] matrix = new int[n][n];

	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	                matrix[i][j] = sc.nextInt();

	        System.out.println("Original Matrix:");
	        displayMatrix(matrix);

	        System.out.println("Transpose:");
	        displayMatrix(transpose(matrix));

	        if (n == 2) {
	            int det = determinant2x2(matrix);
	            System.out.println("Determinant: " + det);
	            System.out.println("Inverse:");
	            displayMatrix(inverse2x2(matrix, det));
	        } else if (n == 3) {
	            int det = determinant3x3(matrix);
	            System.out.println("Determinant: " + det);
	            System.out.println("Inverse:");
	            displayMatrix(inverse3x3(matrix, det));
	        }
	        sc.close();
	    }

	    static int[][] transpose(int[][] m) {
	        int n = m.length;
	        int[][] t = new int[n][n];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	                t[j][i] = m[i][j];
	        return t;
	    }

	    static int determinant2x2(int[][] m) {
	        return m[0][0] * m[1][1] - m[0][1] * m[1][0];
	    }

	    static int determinant3x3(int[][] m) {
	        return m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
	             - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
	             + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
	    }

	    static int[][] inverse2x2(int[][] m, int det) {
	        int[][] inv = new int[2][2];
	        inv[0][0] = m[1][1] / det;
	        inv[0][1] = -m[0][1] / det;
	        inv[1][0] = -m[1][0] / det;
	        inv[1][1] = m[0][0] / det;
	        return inv;
	    }

	    static int[][] inverse3x3(int[][] m, int det) {
	        int[][] inv = new int[3][3];

	        inv[0][0] =  (m[1][1]*m[2][2] - m[1][2]*m[2][1]) / det;
	        inv[0][1] = -(m[0][1]*m[2][2] - m[0][2]*m[2][1]) / det;
	        inv[0][2] =  (m[0][1]*m[1][2] - m[0][2]*m[1][1]) / det;
	        inv[1][0] = -(m[1][0]*m[2][2] - m[1][2]*m[2][0]) / det;
	        inv[1][1] =  (m[0][0]*m[2][2] - m[0][2]*m[2][0]) / det;
	        inv[1][2] = -(m[0][0]*m[1][2] - m[0][2]*m[1][0]) / det;
	        inv[2][0] =  (m[1][0]*m[2][1] - m[1][1]*m[2][0]) / det;
	        inv[2][1] = -(m[0][0]*m[2][1] - m[0][1]*m[2][0]) / det;
	        inv[2][2] =  (m[0][0]*m[1][1] - m[0][1]*m[1][0]) / det;

	        return inv;
	    }

	    static void displayMatrix(int[][] m) {
	        for (int[] row : m) {
	            for (int val : row)
	                System.out.print(val + " ");
	            System.out.println();
	        }
	    }
	}

