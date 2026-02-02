package BridgeLabz_Training.Junit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class MathUtilsTest {
MathUtils utils=new MathUtils();
@Test
void testDivideByZero() {
	assertThrows(ArithmeticException.class,() ->{
		utils.divide(10,0);
	});
}
}
