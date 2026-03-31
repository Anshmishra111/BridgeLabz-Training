package reflection;

import java.lang.reflect.Method;

//---------- CLASS WITH PRIVATE METHOD ----------
class Calculator {

 private int multiply(int a, int b) {
     return a * b;
 }
}

//---------- MAIN CLASS ----------
public class ReflectionPrivateMethodDemo {

 public static void main(String[] args) {

     try {
         Calculator calc = new Calculator();

         // Get Class object
         Class<?> cls = calc.getClass();

         // Access private method
         Method method =
                 cls.getDeclaredMethod("multiply", int.class, int.class);

         method.setAccessible(true); // bypass private access

         // Invoke private method
         int result = (int) method.invoke(calc, 5, 4);

         System.out.println("Result of multiplication: " + result);

     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}
