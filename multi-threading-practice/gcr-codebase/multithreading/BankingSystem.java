package multithreading;
import java.util.Date;

class BankAccount {
    private int balance = 10000;

    // synchronized method to avoid race condition
    public synchronized boolean withdraw(String customer, int amount) {
        System.out.println("[" + customer + "] Attempting to withdraw " + amount);

        if (balance >= amount) {
            try {
                Thread.sleep(500); // simulate processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println("Transaction successful: " + customer +
                    ", Amount: " + amount +
                    ", Balance: " + balance +
                    ", Time: " + new Date());
            return true;
        } else {
            System.out.println("Transaction failed: " + customer +
                    ", Insufficient balance, Balance: " + balance +
                    ", Time: " + new Date());
            return false;
        }
    }
}

class Transaction implements Runnable {
    BankAccount account;
    int amount;
    String customerName;

    Transaction(BankAccount account, String customerName, int amount) {
        this.account = account;
        this.customerName = customerName;
        this.amount = amount;
    }

    public void run() {
        account.withdraw(customerName, amount);
    }
}

public class BankingSystem {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Thread t1 = new Thread(new Transaction(account, "Customer-1", 3000));
        Thread t2 = new Thread(new Transaction(account, "Customer-2", 4000));
        Thread t3 = new Thread(new Transaction(account, "Customer-3", 2000));
        Thread t4 = new Thread(new Transaction(account, "Customer-4", 5000));
        Thread t5 = new Thread(new Transaction(account, "Customer-5", 1500));

        // Display thread state before start
        System.out.println(t1.getName() + " State: " + t1.getState());
        System.out.println(t2.getName() + " State: " + t2.getState());
        System.out.println(t3.getName() + " State: " + t3.getState());
        System.out.println(t4.getName() + " State: " + t4.getState());
        System.out.println(t5.getName() + " State: " + t5.getState());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
