package scenario_based;

import java.util.PriorityQueue;

//Custom Exception
class InvalidBackupPathException extends Exception {
 InvalidBackupPathException(String message) {
     super(message);
 }
}

//BackupTask class using Comparable
class BackupTask implements Comparable<BackupTask> {
 String path;
 int priority; // higher value = higher priority

 BackupTask(String path, int priority) {
     this.path = path;
     this.priority = priority;
 }

 // Sort by priority (descending)
 @Override
 public int compareTo(BackupTask other) {
     return Integer.compare(other.priority, this.priority);
 }

 void execute() {
     System.out.println("Backing up: " + path +
             " | Priority: " + priority);
 }
}

public class FileBackUpScheduler {

 static PriorityQueue<BackupTask> taskQueue = new PriorityQueue<>();

 // Add backup task
 static void addBackupTask(String path, int priority)
         throws InvalidBackupPathException {

     if (path == null || path.isEmpty()) {
         throw new InvalidBackupPathException("Invalid backup path!");
     }

     taskQueue.add(new BackupTask(path, priority));
 }

 // Execute backup tasks
 static void executeBackups() {
     System.out.println("\nExecuting backup tasks:");

     while (!taskQueue.isEmpty()) {
         BackupTask task = taskQueue.poll();
         task.execute();
     }
 }

 public static void main(String[] args) {

     try {
         addBackupTask("/system/config", 5);   // critical
         addBackupTask("/user/documents", 3);
         addBackupTask("/media/photos", 1);
         addBackupTask("", 4); // invalid path

     } catch (InvalidBackupPathException e) {
         System.out.println(e.getMessage());
     }

     executeBackups();
 }
}
