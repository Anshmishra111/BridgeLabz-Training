import java.util.Scanner;
public class RocketLaunchCountdoenForLoop {
    public static void main(String[] args) {
        int countdownStart;
        Scanner sc = new Scanner(System.in);
        countdownStart = sc.nextInt();
        for (int i = countdownStart; i >= 1; i--) {
            System.out.println(i);
     
        }
        sc.close();
    }
    
}
