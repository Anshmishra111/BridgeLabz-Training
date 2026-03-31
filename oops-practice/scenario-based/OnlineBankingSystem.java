package scenariobased;
import java.util.ArrayList;
import java.util.List;

// ---------- Custom Exception ----------
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// ---------- Abstraction ----------
interface BankService {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientBalanceException;
    double calculateInterest();
}

// ---------- Base Account ----------
abstract class Account implements BankService {
    String accountNumber;
    double balance;
    List<String> transactions = new ArrayList<>();

    Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // synchronized for thread safety
    public synchronized void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: ₹" + amount);
    }

    public synchronized void withdraw(double amount)
            throws InsufficientBalanceException {

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance for withdrawal");
        }
        balance -= amount;
        transactions.add("Withdrawn: ₹" + amount);
    }

    synchronized double getBalance() {
        return balance;
    }

    void showTransactions() {
        System.out.println("Transaction History:");
        for (String t : transactions) {
            System.out.println(t);
        }
    }
}

// ---------- Savings Account ----------
class SavingsAccount extends Account {

    SavingsAccount(String accNo, double bal) {
        super(accNo, bal);
    }

    // Polymorphism: Savings interest
    public double calculateInterest() {
        return balance * 0.04; // 4%
    }
}

// ---------- Current Account ----------
class CurrentAccount extends Account {

    CurrentAccount(String accNo, double bal) {
        super(accNo, bal);
    }

    // Polymorphism: Current interest
    public double calculateInterest() {
        return balance * 0.02; // 2%
    }
}

// ---------- Multithreading ----------
class TransactionThread extends Thread {
    Account account;
    double amount;
    boolean isDeposit;

    TransactionThread(Account account, double amount, boolean isDeposit) {
        this.account = account;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    public void run() {
        try {
            if (isDeposit) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
        } catch (InsufficientBalanceException e) {
            System.out.println("Transaction Failed: " + e.getMessage());
        }
    }
}

// ---------- Main Banking System ----------
public class OnlineBankingSystem {

    public static void main(String[] args) {

        Account savings = new SavingsAccount("SAV101", 5000);
        Account current = new CurrentAccount("CUR201", 3000);

        // Concurrent transactions
        Thread t1 = new TransactionThread(savings, 2000, true);
        Thread t2 = new TransactionThread(savings, 1000, false);
        Thread t3 = new TransactionThread(current, 500, false);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display account details
        System.out.println("\nSavings Account Balance: ₹" +
                savings.getBalance());
        System.out.println("Savings Interest: ₹" +
                savings.calculateInterest());
        savings.showTransactions();

        System.out.println("\nCurrent Account Balance: ₹" +
                current.getBalance());
        System.out.println("Current Interest: ₹" +
                current.calculateInterest());
        current.showTransactions();
    }
}

