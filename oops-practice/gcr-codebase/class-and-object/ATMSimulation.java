package classAndObject;

public class ATMSimulation {

	    // Attributes
	    String accountHolder;
	    String accountNumber;
	    double balance;

	    // Constructor
	    ATMSimulation(String accountHolder, String accountNumber, double balance) {
	        this.accountHolder = accountHolder;
	        this.accountNumber = accountNumber;
	        this.balance = balance;
	    }

	    // Method to display current balance
	    void displayBalance() {
	        System.out.println("Current balance: " + balance);
	    }

	    // Method to deposit money
	    void deposit(double amount) {
	        balance = balance + amount;
	        System.out.println("Deposited: " + amount);
	        displayBalance();
	    }

	    // Method to withdraw money
	    void withdraw(double amount) {
	        if (amount <= balance) {
	            balance = balance - amount;
	            System.out.println("Withdraw amount is : " + amount);
	            displayBalance();
	        } else {
	            System.out.println("Insufficient balance");
	        }
	    }

	    public static void main(String[] args) {

	        System.out.println("\t\t\tState of Chennai\t\t\t");

	        // Create bank account with initial balance
	        ATMSimulation account = new ATMSimulation(
	                "Ramesh", "ACC001", 700.0
	        );

	        // Display initial balance
	        account.displayBalance();

	        // Deposit money
	        account.deposit(200.0);

	        // Withdraw money
	        account.withdraw(100.0);

	        // Attempt to withdraw more than balance
	        account.withdraw(1000.0);
	    }
	}



