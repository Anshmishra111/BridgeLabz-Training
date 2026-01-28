package exceptions;

public class ExceptionPropagationInMethods {
	    static void method1() {
	        // Exception occurs here
	        int result = 10 / 0;
	    }

	    static void method2() {
	        // Calls method1()
	        method1();
	    }

	    public static void main(String[] args) {

	        try {
	            // Calls method2()
	            method2();

	        } catch (ArithmeticException e) {
	            System.out.println("Handled exception in main");
	        }
	    }
	}


