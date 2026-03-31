package inheritance;

public class BankAccount {
	    int accountNumber;
	    double balance;

	    BankAccount(int accountNumber, double balance) {
	        this.accountNumber = accountNumber;
	        this.balance = balance;
	    }
	}

	class SavingsAccount extends BankAccount {
	    double interestRate;

	    SavingsAccount(int accountNumber, double balance, double rate) {
	        super(accountNumber, balance);
	        this.interestRate = rate;
	    }

	    void displayAccountType() {
	        System.out.println("Savings Account");
	    }
	}

	class CheckingAccount extends BankAccount {
	    double withdrawalLimit;

	    CheckingAccount(int accountNumber, double balance, double limit) {
	        super(accountNumber, balance);
	        this.withdrawalLimit = limit;
	    }

	    void displayAccountType() {
	        System.out.println("Checking Account");
	    }
	}

	class FixedDepositAccount extends BankAccount {
	    int maturityPeriod;

	    FixedDepositAccount(int accountNumber, double balance, int years) {
	        super(accountNumber, balance);
	        this.maturityPeriod = years;
	    }

	    void displayAccountType() {
	        System.out.println("Fixed Deposit Account");
	    }
	}

