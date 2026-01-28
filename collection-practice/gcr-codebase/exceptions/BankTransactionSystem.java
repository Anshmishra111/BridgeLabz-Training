package exceptions;

import java.util.Scanner;

//Custom Checked Exception
class InsufficientBalanceException extends Exception {
 InsufficientBalanceException(String message) {
     super(message);
 }
}

public class BankTransactionSystem {

 static double balance = 5000;

 // Withdraw method
 static void withdraw(double amount)
         throws InsufficientBalanceException {

     if (amount < 0) {
         throw new IllegalArgumentException("Invalid amount!");
     }

     if (amount > balance) {
         throw new InsufficientBalanceException("Insufficient balance!");
     }

     balance -= amount;
     System.out.println("Withdrawal successful, new balance: " + balance);
 }

 public static void main(String[] args) {

     Scanner sc = new Scanner(System.in);

     try {
         System.out.print("Enter withdrawal amount: ");
         double amount = sc.nextDouble();

         withdraw(amount);

     } catch (InsufficientBalanceException e) {
         System.out.println(e.getMessage());

     } catch (IllegalArgumentException e) {
         System.out.println(e.getMessage());

     } finally {
         sc.close();
     }
 }
}
