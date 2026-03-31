package BridgeLabz_Training.Junit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class StringUtilsTest {
StringUtils utils=new StringUtils();
@Test
void testReverse() {
	assertEquals("olleh",utils.reverse("hello"));
}
@Test
void testPalindromeTrue() {
	assertTrue(utils.isPalindrome("madam"));
}
@Test
void testPalindromeFalse() {
	assertFalse(utils.isPalindrome("hello"));
}
@Test
void testUpperCase() {
	assertEquals("JAVA",utils.toUpperCase("java"));

}
}