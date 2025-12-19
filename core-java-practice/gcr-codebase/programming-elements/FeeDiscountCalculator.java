import java.util.Scanner;
public class FeeDiscountCalculator {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        double fee;
        double discPercent;
        System.out.print("Enter the student fee: ");
        fee = sc.nextDouble();
        System.out.print("Enter the discount percentage: ");
        discPercent =sc.nextDouble();
        double disc = (fee * discPercent)/100;
        double finalFee = fee-disc;
        System.out.println("The discount amount is INR " + disc +" and final discounted fee is INR " + finalFee);
   }
}
