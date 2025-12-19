import java.util.Scanner;

public class GreatestFactorOfNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int greatestFactor = 1;
        if (number <= 1) {
            System.out.println("No factor other than itself exists");
        } else {
            for (int i = number - 1; i >= 1; i--) {
                if (number % i == 0) {
                    greatestFactor = i;
                    break;
                }
            }
            System.out.println("The greatest factor of " + number +  " besides itself is " + greatestFactor);
        }

        sc.close();
    }
}

        
