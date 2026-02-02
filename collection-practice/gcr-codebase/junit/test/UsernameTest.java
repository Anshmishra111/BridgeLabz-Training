package BridgeLabz_Training.Junit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class UsernameTest {
UserRegistration registration=new UserRegistration();
@Test
void testValidregistration() {
	assertTrue(registration.registerUser("ansh123",
            "ansh@example.com",
            "Password1"));
}
@Test
void tsetInvalidUserName() {
	assertThrows(IllegalArgumentException.class,() ->{
		registration.registerUser("abc",
                "user@example.com",
                "Password1");
	});
}
@Test
void testInvalidPassword() {
	assertThrows(IllegalArgumentException.class,() ->{
		registration.registerUser("validUser",
                "user@example.com",
                "pass");
	});
}
}
