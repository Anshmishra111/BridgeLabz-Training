package constructors;

public class BankAccountManagement {
	    public static void main(String[] args) {

	        // Create BankAccount object
	        BankAccount account = new BankAccount(
	                "ACC1001", "Ansh", 5000.0
	        );

	        account.displayAccountDetails();

	        // Modify balance using public methods
	        account.deposit(2000.0);
	        account.withdraw(1500.0);

	        // Create SavingsAccount object
	        SavingsAccount savings = new SavingsAccount(
	                "SAV2001", "Rohit", 10000.0, 5.0
	        );

	        savings.displaySavingsDetails();
	    }
	}

	// -------------------- BankAccount Class --------------------
	class BankAccount {

	    public String accountNumber;     // public
	    protected String accountHolder;  // protected
	    private double balance;           // private

	    BankAccount(String accountNumber, String accountHolder, double balance) {
	        this.accountNumber = accountNumber;
	        this.accountHolder = accountHolder;
	        this.balance = balance;
	    }

	    // Getter for balance
	    public double getBalance() {
	        return balance;
	    }

	    // Deposit money
	    public void deposit(double amount) {
	        balance += amount;
	        System.out.println("Deposited: " + amount);
	        System.out.println("Current Balance: " + balance);
	    }

	    // Withdraw money
	    public void withdraw(double amount) {
	        if (amount <= balance) {
	            balance -= amount;
	            System.out.println("Withdrawn: " + amount);
	            System.out.println("Current Balance: " + balance);
	        } else {
	            System.out.println("Insufficient balance");
	        }
	    }

	    void displayAccountDetails() {
	        System.out.println("Account Number : " + accountNumber);
	        System.out.println("Account Holder : " + accountHolder);
	        System.out.println("Balance        : " + balance);
	        System.out.println("---------------------------");
	    }
	}

	// -------------------- SavingsAccount Class --------------------
	class SavingsAccount extends BankAccount {

	    double interestRate;

	    SavingsAccount(String accNo, String holder, double bal, double rate) {
	        super(accNo, holder, bal);
	        this.interestRate = rate;
	    }

	    // Demonstrates access to public & protected members
	    void displaySavingsDetails() {
	        System.out.println("Savings Account Number : " + accountNumber); // public
	        System.out.println("Account Holder         : " + accountHolder); // protected
	        System.out.println("Interest Rate          : " + interestRate + "%");
	        System.out.println("Balance                : " + getBalance()); // private via getter
	        System.out.println("---------------------------");
	    }
	}


