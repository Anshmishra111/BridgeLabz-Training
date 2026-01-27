package streams;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class SerializationSaveRetrieveAnObject {
//employee class
  static class Employee implements Serializable{
	  private static final long serialVersionUID=1L;
	  int id;
	  String name;
	  String department;
	  double salary;
	  
	  Employee(int id,String name,String department,double salary){
		  this.id=id;
		  this.name=name;
		  this.department=department;
		  this.salary=salary;
	  }
	  @Override
	  public String toString() {
		  return id + " | " + name + " | " + department + " | " + salary;
	  }
	  
	  
  }
  public static void main(String[] args) {
	  String fileName="employees.dat";
	  List<Employee> employees=new ArrayList<>();
	  employees.add(new Employee(101,"Alice","HR",50000));
	  employees.add(new Employee(102,"Bob","IT",65000));
      employees.add(new Employee(103, "Carol", "Finance", 60000));
     //serialization
      try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fileName))) {
    	  oos.writeObject(employees);
    	  System.out.println("Employees serialized successfully");
    	  
    	  
      }catch(IOException e) {
    	  System.out.println("Error handling serialization");
    	  e.printStackTrace();
      }
      //deserialization
      try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fileName))){
    	  List<Employee> storedEmployees=(List<Employee>) ois.readObject();
    	  System.out.println("\nDeserialized employee data");
    	  for(Employee emp:storedEmployees) {
    		  System.out.println(emp);
    	  }
    	  
      }catch(IOException | ClassNotFoundException e) {
    	  System.out.println("Error during deserialization");
    	  e.printStackTrace();
    	  
      }
  }
}
