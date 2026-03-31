package reflection;

import java.lang.reflect.Field;

//---------- CLASS WITH PRIVATE FIELD ----------
class Person {

 private int age = 25;
}

//---------- MAIN CLASS ----------
public class ReflectionPrivateFieldDemo {

 public static void main(String[] args) {

     try {
         Person person = new Person();

         // Get Class object
         Class<?> cls = person.getClass();

         // Access private field
         Field field = cls.getDeclaredField("age");
         field.setAccessible(true); // bypass private access

         // Retrieve value
         int currentAge = field.getInt(person);
         System.out.println("Original Age: " + currentAge);

         // Modify value
         field.setInt(person, 30);

         // Retrieve updated value
         int updatedAge = field.getInt(person);
         System.out.println("Updated Age: " + updatedAge);

     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}
