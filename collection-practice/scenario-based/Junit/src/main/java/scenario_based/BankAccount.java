package scenario_based;

public class BankAccount {

    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws Exception {
        if (amount < 0)
            throw new Exception("Deposit amount cannot be negative");

        balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (amount > balance)
            throw new Exception("Insufficient funds.");

        balance -= amount;
    }
}
