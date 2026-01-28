package employeeLeave;
import java.util.*;
class InsufficientLeaveBalanceException extends Exception {
	 public InsufficientLeaveBalanceException(String msg) {
	     super(msg);

	 }
}
	class Employee{
		int id;
		String name;
		int leaveBalance;
	Employee(int id,String name,int leaveBalance) {
		this.id=id;
		this.name=name;
		this.leaveBalance=leaveBalance;
	}
	 }
	class LeaveRequest{
		int days;
		int empId;
	LeaveRequest(int days,int empId){
		this.days=days;
		this.empId=empId;
	
}
}
	public class EmployeeLeaveManagementSystem {
		static Map<Integer,Employee> employees=new HashMap<>();
		static List<LeaveRequest> leaverequests=new ArrayList<>();
		public static void main(String[] args) {
			     // Add employees
			     employees.put(1,new Employee(1, "Himanshu", 11));
			     employees.put(2,new Employee(2, "Ansh", 5));
			     LeaveRequest lr=new LeaveRequest(4,1);
			     //crt leave
			     leaverequests.add(lr);
			     try {
			         approveLeave(lr);
			     } catch (InsufficientLeaveBalanceException e) {
			         System.out.println(e.getMessage());
			     }
			 }

		static void approveLeave(LeaveRequest lr) throws InsufficientLeaveBalanceException{
		    Employee emp = employees.get(lr.empId);


		    if (emp.leaveBalance < lr.days) { throw new InsufficientLeaveBalanceException( "Insufficient leave balance for employee " + emp.name);
		    }

		    emp.leaveBalance =emp.leaveBalance-lr.days;
		    System.out.println("Leave approved for " + emp.name);
		    System.out.println("Remaining balance: " + emp.leaveBalance);
		}
	}
