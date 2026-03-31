import java.util.Scanner;
public class TotalPrice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double unitP;
        int quantity;
        System.out.print("Enter unit price: ");
        unitP = sc.nextDouble();
        System.out.print("Enter quantity: ");
        quantity = sc.nextInt();
        double totalP = unitP * quantity;
        System.out.println("The total purchase price is INR " + totalP + " if the quantity " + quantity + " and unit price is INR " + unitP);
        sc.close();
    }
}
