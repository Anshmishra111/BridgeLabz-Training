package annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime{
}

	class PerformanceService{
		@LogExecutionTime
		
		
			public void fastMethod() {
				for(int i=0;i<100000;i++) {
			}
		}
			@LogExecutionTime
			public void slowMethod() {
				for(int i=0;i<5000000;i++) {
					
				}
			}
			public void normalMethod() {
				
			}
	}

public class LogExecutionTimeDemo {
public static void main(String[] args) throws Exception {
	PerformanceService service=new PerformanceService();
	Class<?> cls=service.getClass();
	for(Method method:cls.getDeclaredMethods()){
		if(method.isAnnotationPresent(LogExecutionTime.class)) {
			long startTime = System.nanoTime();

            method.invoke(service);   // execute method

            long endTime = System.nanoTime();

            System.out.println("Method: " + method.getName());
            System.out.println("Execution Time: " +
                    (endTime - startTime) + " ns");
            System.out.println("------------------------");
        
		}
	}
}
}
