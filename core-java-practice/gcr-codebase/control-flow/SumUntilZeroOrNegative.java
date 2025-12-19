import java.util.Scanner;

public class SumUntilZeroOrNegative {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double sum = 0.0;
        while (true) {
            double value = sc.nextDouble();
            if (value <= 0) {
                break;
            }

            sum = sum + value;
        }

        System.out.println("The total sum is " + sum);

        sc.close();
    }
}

    
