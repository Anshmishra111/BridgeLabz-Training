import java.util.Scanner;

public class RocketLaunchCountdown {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int counter;
        System.out.print("Enter countdown start number: ");
        counter = sc.nextInt();
        while (counter >= 0) {
            System.out.println(counter);
            counter--;
        }
        sc.close();
        
    }
    
}
