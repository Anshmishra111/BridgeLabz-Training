package annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod{
	String level() default "HIGH";
}
class TaskService{
@ImportantMethod
public void processTask() {
    System.out.println("Processing task");
}

@ImportantMethod(level = "MEDIUM")
public void generateReport() {
    System.out.println("Generating report");
}

public void helperMethod() {
    System.out.println("Helper method");
}
}

//---------- MAIN CLASS ----------
public class ImportantMethodDemo {

public static void main(String[] args) {

    Class<TaskService> cls = TaskService.class;

    Method[] methods = cls.getDeclaredMethods();

    for (Method method : methods) {
        if (method.isAnnotationPresent(ImportantMethod.class)) {

            ImportantMethod imp =
                    method.getAnnotation(ImportantMethod.class);

            System.out.println("Method Name: " + method.getName());
            System.out.println("Importance Level: " + imp.level());
            System.out.println();
        }
    }
}
}