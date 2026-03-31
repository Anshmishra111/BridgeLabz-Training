package annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;
//custom annotataion
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo{
	String task();
	String assignedTo();
	String priority() default "MEDIUM";
}
//class using todo
class ProjectTasks{
	@Todo(task="Implement login feature",
			assignedTo="Ansh",priority="HIGH")
   public void loginFeature() {}
		@Todo(task="Optimize database queries",
				assignedTo="Neha",
				priority="LOW")
		public void optimizeDB() {}
			
		}
	
	

public class TodoAnnotationdemo {
	public static void main(String[] args) {
		Class<ProjectTasks> cls=ProjectTasks.class;
		Method[] methods=cls.getDeclaredMethods();
		for(Method method:methods) {
			if(method.isAnnotationPresent(Todo.class)) {
				Todo todo=method.getAnnotation(Todo.class);
				System.out.println("Method: " + method.getName());
                System.out.println("Task: " + todo.task());
                System.out.println("Assigned To: " + todo.assignedTo());
                System.out.println("Priority: " + todo.priority());
                System.out.println("-------------------------");
			}
		}
	}
}


