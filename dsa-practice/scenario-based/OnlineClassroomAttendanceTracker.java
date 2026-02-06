package scenario_based;

import java.util.*;

//Custom Exception
class DuplicateAttendanceException extends Exception {
 DuplicateAttendanceException(String msg) {
     super(msg);
 }
}

//Attendance Service class
class AttendanceService {
 Map<String, Set<String>> attendance = new HashMap<>();

 void markAttendance(String sessionId, String studentId)
         throws DuplicateAttendanceException {

     if (!attendance.containsKey(sessionId)) {
         attendance.put(sessionId, new HashSet<>());
     }

     Set<String> students = attendance.get(sessionId);

     if (students.contains(studentId))
         throw new DuplicateAttendanceException("Attendance already marked");

     students.add(studentId);
 }

 void removeAttendance(String sessionId, String studentId) {
     if (attendance.containsKey(sessionId)) {
         attendance.get(sessionId).remove(studentId);
     }
 }

 void displayAttendance() {
     for (String sessionId : attendance.keySet()) {
         System.out.println("Session " + sessionId +
                 " : " + attendance.get(sessionId));
     }
 }
}

//Main class
public class OnlineClassroomAttendanceTracker {
 public static void main(String[] args) {

     AttendanceService service = new AttendanceService();

     try {
         service.markAttendance("S1", "ST01");
         service.markAttendance("S1", "ST02");
         service.markAttendance("S1", "ST01"); // Exception
     } catch (DuplicateAttendanceException e) {
         System.out.println(e.getMessage());
     }

     service.displayAttendance();
     service.removeAttendance("S1", "ST02");
     service.displayAttendance();
 }
}
