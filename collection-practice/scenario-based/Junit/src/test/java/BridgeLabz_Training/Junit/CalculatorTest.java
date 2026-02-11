package BridgeLabz_Training.Junit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class CalculatorTest {
Calculator calc=new Calculator();
@Test
void testAdd() {
	assertEquals(10,calc.add(7, 3));
}
@Test
void testSubtract() {
	assertEquals(4,calc.subtract(7, 3));
}
@Test
void testMultiply() {
	assertEquals(21,calc.multiply(7,3));
}
@Test
void testDivide() {
	assertEquals(2,calc.divide(6,3));
}
@Test 
void testDivideByZero(){
	assertThrows(ArithmeticException.class, ()->{
		calc.divide(10, 0);
	});
	
}
}
