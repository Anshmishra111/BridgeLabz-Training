package scenariobased;

public class BankAccountManager {
	    // Fields / Properties
	    String accountNumber;
	    double balance;

	    // Constructor
	    BankAccountManager(String accountNumber, double balance) {
	        this.accountNumber = accountNumber;
	        this.balance = balance;
	    }

	    // Method to deposit money
	    void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	            System.out.println("Deposited: " + amount);
	        } else {
	            System.out.println("Invalid deposit amount");
	        }
	    }

	    // Method to withdraw money (prevent overdraft)
	    void withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	            System.out.println("Withdrawn: " + amount);
	        } else {
	            System.out.println("Insufficient balance or invalid amount");
	        }
	    }

	    // Method to check balance
	    void checkBalance() {
	        System.out.println("Account Number: " + accountNumber);
	        System.out.println("Current Balance: " + balance);
	    }

	    public static void main(String[] args) {

	        // Create BankAccount object
	    	BankAccountManager account = new BankAccountManager("ACC101", 5000.0);

	        // Perform operations
	        account.checkBalance();
	        account.deposit(2000.0);
	        account.withdraw(1000.0);
	        account.withdraw(7000.0); // overdraft attempt
	        account.checkBalance();
	    }
	}


