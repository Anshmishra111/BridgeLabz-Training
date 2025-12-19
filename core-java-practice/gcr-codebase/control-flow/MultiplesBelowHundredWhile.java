import java.util.Scanner;

public class MultiplesBelowHundredWhile {
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        if (number <= 0 || number >= 100) {
            System.out.println("Please enter a positive number less than 100");
        } else {
            int counter = 100;
            while (counter > 1) {
                if (counter % number == 0) {
                    System.out.println(counter);
                }
                counter--;
            }
        }

        sc.close();
    }
}

        
