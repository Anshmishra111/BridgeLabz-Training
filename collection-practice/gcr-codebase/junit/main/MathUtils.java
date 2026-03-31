package BridgeLabz_Training.Junit;

public class MathUtils {
public int divide(int a,int b) {
	if(b==0) {
		throw new ArithmeticException("cannot divide by zero");
	}
	return a/b;
}
}
