package BridgeLabz_Training.Junit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class PasswordValidatorTest {
PasswordValidator validator=new PasswordValidator();
	@Test
	void testValidPassword() {
		assertTrue(validator.isValid("Password1"));
	}
	@Test
	void tsetTooShortPassword() {
		assertFalse(validator.isValid("Pass1"));
	}
	@Test
	void tsetNoUppercase() {
		assertFalse(validator.isValid("password1"));
	}
	@Test
	void tsetNoDigit() {
		assertFalse(validator.isValid("Password"));
	}
	@Test
	void testNullPassword() {
		assertFalse(validator.isValid(null));
	}
}
