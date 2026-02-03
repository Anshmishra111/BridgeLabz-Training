package reflection;

import java.lang.reflect.Field;

//---------- CLASS WITH PRIVATE STATIC FIELD ----------
class Configuration {

 private static String API_KEY = "DEFAULT_KEY";

 public static String getApiKey() {
     return API_KEY;
 }
}

//---------- MAIN CLASS ----------
public class ReflectionStaticFieldDemo {

 public static void main(String[] args) {

     try {
         // Get Class object
         Class<?> cls = Configuration.class;

         // Access private static field
         Field field = cls.getDeclaredField("API_KEY");
         field.setAccessible(true); // bypass private access

         // Modify static field (null for static)
         field.set(null, "NEW_SECRET_KEY");

         // Retrieve updated value
         System.out.println("Updated API_KEY: " +
                 Configuration.getApiKey());

     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}
