import java.util.Scanner;
public class MaxHandshakes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int StudNum = sc.nextInt();
        int handshakes = (StudNum * (StudNum - 1)) / 2;
        System.out.println("The maximum number of possible handshakes is " + handshakes);
        sc.close();
    }
}
