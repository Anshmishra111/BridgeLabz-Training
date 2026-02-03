package reflection;

import java.lang.reflect.Method;
import java.util.Scanner;

// ---------- CLASS WITH MULTIPLE METHODS ----------
class MathOperations {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

// ---------- MAIN CLASS ----------
public class DynamicMethodInvocationDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            MathOperations obj = new MathOperations();

            System.out.print("Enter method name (add / subtract / multiply): ");
            String methodName = sc.next();

            System.out.print("Enter first number: ");
            int a = sc.nextInt();

            System.out.print("Enter second number: ");
            int b = sc.nextInt();

            // Get Class object
            Class<?> cls = obj.getClass();

            // Get method dynamically by name
            Method method = cls.getMethod(methodName, int.class, int.class);

            // Invoke method dynamically
            int result = (int) method.invoke(obj, a, b);

            System.out.println("Result: " + result);

        } catch (NoSuchMethodException e) {
            System.out.println("Invalid method name!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
