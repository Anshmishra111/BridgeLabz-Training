import java.util.Scanner;

public class GreatestFacOfNumUsingWhileLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int greatestFactor = 1;
        if (number <= 1) {
            System.out.println("No factor other than itself exists");
        } else {

            int counter = number - 1;
            while (counter >= 1) {
                if (number % counter == 0) {
                    greatestFactor = counter;
                    break;
                }
                counter--;
            }
            System.out.println(
                "The greatest factor of " + number +" besides itself is " + greatestFactor
            );
        }

        sc.close();
    }
}

        
