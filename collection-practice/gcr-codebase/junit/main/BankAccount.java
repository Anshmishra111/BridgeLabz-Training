package BridgeLabz_Training.Junit;

public class BankAccount {
private double balance=0;
//deposit money
public void deposit(double amount) {
	if(amount<0) {
		throw new IllegalArgumentException("Invalid deposit money");
	}
	balance+=amount;
}
//withdraw money
public void withdraw(double amount) {
	if(amount>balance) {
		throw new IllegalArgumentException("InsufficientFunds");
	}
	balance-=balance;
}
//get current balance
public double getBalance() {
	return balance;
}
}
