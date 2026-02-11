package scenario_based;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    void Test_Deposit_ValidAmount() throws Exception {
        BankAccount account = new BankAccount(1000);
        account.deposit(500);

        assertEquals(1500, account.getBalance()); // ONE assert
    }

    @Test
    void Test_Deposit_NegativeAmount() {
        BankAccount account = new BankAccount(1000);

        Exception ex = assertThrows(Exception.class, () -> {
            account.deposit(-100);
        });

        assertEquals("Deposit amount cannot be negative", ex.getMessage());
    }

    @Test
    void Test_Withdraw_ValidAmount() throws Exception {
        BankAccount account = new BankAccount(1000);
        account.withdraw(400);

        assertEquals(600, account.getBalance()); // ONE assert
    }

    @Test
    void Test_Withdraw_InsufficientFunds() {
        BankAccount account = new BankAccount(500);

        Exception ex = assertThrows(Exception.class, () -> {
            account.withdraw(1000);
        });

        assertEquals("Insufficient funds.", ex.getMessage());
    }
}
