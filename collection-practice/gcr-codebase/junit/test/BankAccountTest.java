package BridgeLabz_Training.Junit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
	BankAccount account=new BankAccount();
	@Test
	void testDeposit() {
		account.deposit(1000);
		assertEquals(1000,account.getBalance());
	}
	@Test
	void testWithdraw() {
		account.deposit(4000);
		account.withdraw(1000);
		assertEquals(0,account.getBalance());
	}
	@Test
	void testWithdrawInsufficientFunds() {
		account.deposit(500);
		assertThrows(IllegalArgumentException.class,() ->{
			account.withdraw(1000);
		});
	}

}
