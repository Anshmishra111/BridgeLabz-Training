package encapsulation_polymorphism;
	abstract class BankAccount {
	    private String accountNumber;
	    private String holderName;
	    private double balance;

	    public BankAccount(String number, String name, double balance) {
	        this.accountNumber = number;
	        this.holderName = name;
	        this.balance = balance;
	    }

	    public void deposit(double amount) {
	        balance += amount;
	    }

	    public void withdraw(double amount) {
	        if (amount <= balance)
	            balance -= amount;
	    }

	    public abstract double calculateInterest();

	    public double getBalance() {
	        return balance;
	    }
	}

	interface Loanable {
	    void applyForLoan();
	    boolean calculateLoanEligibility();
	}

	class SavingsAccount extends BankAccount implements Loanable {
	    public SavingsAccount(String number, String name, double balance) {
	        super(number, name, balance);
	    }

	    public double calculateInterest() {
	        return getBalance() * 0.04;
	    }

	    public void applyForLoan() {
	        System.out.println("Savings Account loan applied");
	    }

	    public boolean calculateLoanEligibility() {
	        return getBalance() > 10000;
	    }
	}

	public class BankingSystem {
	    public static void main(String[] args) {
	        BankAccount acc = new SavingsAccount("SB001", "Ravi", 12000);
	        acc.deposit(3000);
	        acc.withdraw(2000);
	        System.out.println("Interest: ₹" + acc.calculateInterest());
	    }
	}

