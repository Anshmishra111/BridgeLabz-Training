// package core-java-practice.gcr-codebase.arrays;
import java.util.Scanner;
public class ArraySumWithLimit {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    double[] numbers = new double[10];
        double total = 0.0;
        int index = 0;
        while (true) {

            double value = sc.nextDouble();
            if (value <= 0) {
                break;
            }
            if (index == 10) {
                break;
            }
            numbers[index] = value;
            index++;
        }
        for (int i = 0; i < index; i++) {
            total = total + numbers[i];
        }
        System.out.println("\nStored Numbers:");
        for (int i = 0; i < index; i++) {
            System.out.println(numbers[i]);
        }
        System.out.println("\nTotal Sum = " + total);

        sc.close();
    }
}
