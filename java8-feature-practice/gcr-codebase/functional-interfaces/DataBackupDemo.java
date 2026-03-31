package functional_interfaces;

//Marker Interface
interface BackupSerializable {
 // no methods
}

//Class marked for backup
class UserData implements BackupSerializable {
 String username;
 int userId;

 UserData(String username, int userId) {
     this.username = username;
     this.userId = userId;
 }
}

//Backup Processor
class BackupService {
 void backup(Object obj) {
     if (obj instanceof BackupSerializable) {
         System.out.println("Object backed up successfully");
     } else {
         System.out.println("Object not eligible for backup");
     }
 }
}

//Main class
public class DataBackupDemo {
 public static void main(String[] args) {

     UserData user = new UserData("Ansh", 101);
     String tempData = "Temporary Data";

     BackupService service = new BackupService();

     service.backup(user);
     service.backup(tempData);
 }
}
