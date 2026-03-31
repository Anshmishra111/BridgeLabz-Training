package annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;

// ---------- CUSTOM ANNOTATION ----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
    String priority();
    String assignedTo();
}

// ---------- CLASS USING ANNOTATION ----------
class TaskManager {

    @TaskInfo(priority = "High", assignedTo = "Ansh")
    public void completeTask() {
        System.out.println("Task completed");
    }
}

// ---------- MAIN CLASS ----------
public class CustomAnnotationDemo {

    public static void main(String[] args) {

        try {
            Class<TaskManager> cls = TaskManager.class;

            Method method = cls.getMethod("completeTask");

            if (method.isAnnotationPresent(TaskInfo.class)) {

                TaskInfo info = method.getAnnotation(TaskInfo.class);

                System.out.println("Priority: " + info.priority());
                System.out.println("Assigned To: " + info.assignedTo());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
