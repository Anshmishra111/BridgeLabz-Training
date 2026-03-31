package scenariobased;
//Custom Exception
class UnsufficientBalanceException extends Exception {
 public UnsufficientBalanceException(String message) {
     super(message);
 }
}

//Transfer Interface (Abstraction)
interface TransferService {
 void transfer(Wallet from, Wallet to, double amount)
         throws UnsufficientBalanceException;
}

//Wallet-to-Wallet Transfer
class WalletTransfer implements TransferService {
 public void transfer(Wallet from, Wallet to, double amount)
         throws UnsufficientBalanceException {

     if (from.getBalance() < amount) {
         throw new UnsufficientBalanceException("Insufficients balance in wallet!");
     }

     from.withdraw(amount);
     to.addMoney(amount);
     System.out.println("Wallet transfer successful.");
 }
}

//Bank-to-Wallet Transfer (Polymorphism)
class BankTransfer implements TransferService {
 public void transfer(Wallet from, Wallet to, double amount) {
     // Bank assumed to have sufficient balance
     to.addMoney(amount);
     System.out.println("Bank transfer successful.");
 }
}

//User Class
class Users {
 private int userId;
 private String name;

 public Users(int userId, String name) {
     this.userId = userId;
     this.name = name;
 }

 public String getName() {
     return name;
 }
}

//Transaction Class
class Transaction {
 String type;
 double amount;

 public Transaction(String type, double amount) {
     this.type = type;
     this.amount = amount;
 }

 public void display() {
     System.out.println(type + " : ₹" + amount);
 }
}

//Wallet Class
class Wallet {
 private int walletId;
 private Users user;
 private double balance;
 private Transaction[] transactions = new Transaction[10];
 private int txnCount = 0;

 public Wallet(int walletId, Users user) {
     this.walletId = walletId;
     this.user = user;
     this.balance = 0;
 }

 public double getBalance() {
     return balance;
 }

 // Add Money
 public void addMoney(double amount) {
     balance += amount;
     transactions[txnCount++] = new Transaction("ADD", amount);
     System.out.println("Money added: ₹" + amount);
 }

 // Withdraw Money
 public void withdraw(double amount) throws UnsufficientBalanceException {
     if (balance < amount) {
         throw new UnsufficientBalanceException("Insufficients balance!");
     }
     balance -= amount;
     transactions[txnCount++] = new Transaction("WITHDRAW", amount);
     System.out.println("Money withdrawn: ₹" + amount);
 }

 // Show Transactions
 public void showTransactions() {
     System.out.println("\nTransaction History for " + user.getName());
     for (int i = 0; i < txnCount; i++) {
         transactions[i].display();
     }
     System.out.println("Current Balance: ₹" + balance);
 }
}

//Main Class
public class DigitalWalletSystem {
 public static void main(String[] args) throws UnsufficientBalanceException {

     // Users
     Users user1 = new Users(1, "Ansh");
     Users user2 = new Users(2, "Rahul");

     // Wallets
     Wallet wallet1 = new Wallet(101, user1);
     Wallet wallet2 = new Wallet(102, user2);

     // Add Money
     wallet1.addMoney(5000);

     try {
         // Withdraw
         wallet1.withdraw(1000);

         // Wallet to Wallet Transfer
         TransferService walletTransfer = new WalletTransfer();
         walletTransfer.transfer(wallet1, wallet2, 2000);

     } catch (UnsufficientBalanceException e) {
         System.out.println(e.getMessage());
     }

     // Bank to Wallet Transfer
     TransferService bankTransfer = new BankTransfer();
     bankTransfer.transfer(null, wallet1, 3000);

     // Transaction History
     wallet1.showTransactions();
     wallet2.showTransactions();
 }
}

