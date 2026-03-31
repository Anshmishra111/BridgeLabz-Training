package annotations;

//Legacy API class
class LegacyAPI {

 @Deprecated
 public void oldFeature() {
     System.out.println("This is the old feature (Deprecated)");
 }

 public void newFeature() {
     System.out.println("This is the new recommended feature");
 }
}

//Main class
public class DeprecatedDemo {

 public static void main(String[] args) {

     LegacyAPI api = new LegacyAPI();

     api.oldFeature();  // ⚠️ Deprecated warning
     api.newFeature();  // ✅ Preferred method
 }
}
