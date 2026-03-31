package method;
import java.util.Random;
import java.util.Scanner;
public class MatrixOperations {
	    public static int[][] generateRandomMatrix(int rows, int cols) {
	        Random rand = new Random();
	        int[][] matrix = new int[rows][cols];
	        for (int i = 0; i < rows; i++)
	            for (int j = 0; j < cols; j++)
	                matrix[i][j] = rand.nextInt(10);
	        return matrix;
	    }

	    public static int[][] addMatrices(int[][] A, int[][] B) {
	        int rows = A.length;
	        int cols = A[0].length;
	        int[][] result = new int[rows][cols];
	        for (int i = 0; i < rows; i++)
	            for (int j = 0; j < cols; j++)
	                result[i][j] = A[i][j] + B[i][j];
	        return result;
	    }

	    public static int[][] subtractMatrices(int[][] A, int[][] B) {
	        int rows = A.length;
	        int cols = A[0].length;
	        int[][] result = new int[rows][cols];
	        for (int i = 0; i < rows; i++)
	            for (int j = 0; j < cols; j++)
	                result[i][j] = A[i][j] - B[i][j];
	        return result;
	    }

	    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
	        int rowsA = A.length;
	        int colsA = A[0].length;
	        int colsB = B[0].length;
	        int[][] result = new int[rowsA][colsB];
	        for (int i = 0; i < rowsA; i++)
	            for (int j = 0; j < colsB; j++)
	                for (int k = 0; k < colsA; k++)
	                    result[i][j] += A[i][k] * B[k][j];
	        return result;
	    }

	    public static void printMatrix(int[][] matrix) {
	        for (int[] row : matrix) {
	            for (int val : row)
	                System.out.print(val + " ");
	            System.out.println();
	        }
	    }

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int rows, cols;
	        System.out.print("Enter number of rows: ");
	        rows = sc.nextInt();
	        System.out.print("Enter number of columns: ");
	        cols = sc.nextInt();

	        int[][] matrixA = generateRandomMatrix(rows, cols);
	        int[][] matrixB = generateRandomMatrix(rows, cols);

	        System.out.println("Matrix A:");
	        printMatrix(matrixA);
	        System.out.println("Matrix B:");
	        printMatrix(matrixB);

	        System.out.println("Addition (A + B):");
	        printMatrix(addMatrices(matrixA, matrixB));

	        System.out.println("Subtraction (A - B):");
	        printMatrix(subtractMatrices(matrixA, matrixB));

	        if (cols == rows) {
	            System.out.println("Multiplication (A * B):");
	            printMatrix(multiplyMatrices(matrixA, matrixB));
	        } else {
	            System.out.println("Multiplication not possible with given dimensions.");
	        }

	        sc.close();
	    }
	}


