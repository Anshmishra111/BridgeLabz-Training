package functional_interfaces;

//Marker Interface
interface SensitiveData {
 // no methods
}

//Class marked as sensitive
class CustomerInfo implements SensitiveData {
 String name;
 String aadhaar;

 CustomerInfo(String name, String aadhaar) {
     this.name = name;
     this.aadhaar = aadhaar;
 }
}

//Encryption Processor
class EncryptionService {
 void process(Object obj) {
     if (obj instanceof SensitiveData) {
         System.out.println("Encrypting sensitive data...");
     } else {
         System.out.println("No encryption required");
     }
 }
}

//Main class
public class SensitiveDataTagging {
 public static void main(String[] args) {

     CustomerInfo customer = new CustomerInfo("Ansh", "1234-5678-9012");
     String logData = "Normal Log";

     EncryptionService service = new EncryptionService();

     service.process(customer);
     service.process(logData);
 }
}
