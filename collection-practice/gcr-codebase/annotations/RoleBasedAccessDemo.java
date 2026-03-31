package annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;

// ---------- CUSTOM ANNOTATION ----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)   // class-level annotation
@interface RoleAllowed {
    String value();
}

// ---------- SERVICE CLASS ----------
@RoleAllowed("ADMIN")
class AdminService {

    public void deleteUser() {
        System.out.println("User deleted successfully!");
    }
}

// ---------- SECURITY MANAGER ----------
class SecurityManager {

    // Simulate current user role
    public static String currentUserRole = "USER"; // change to ADMIN to allow

    public static void invokeSecureMethod(Object obj, String methodName) {

        Class<?> cls = obj.getClass();

        // Check if class has RoleAllowed annotation
        if (cls.isAnnotationPresent(RoleAllowed.class)) {

            RoleAllowed roleAllowed = cls.getAnnotation(RoleAllowed.class);

            if (!roleAllowed.value().equalsIgnoreCase(currentUserRole)) {
                System.out.println("Access Denied!");
                return;
            }
        }

        try {
            Method method = cls.getMethod(methodName);
            method.invoke(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// ---------- MAIN CLASS ----------
public class RoleBasedAccessDemo {

    public static void main(String[] args) {

        AdminService service = new AdminService();

        // Try accessing with USER role
        SecurityManager.currentUserRole = "USER";
        SecurityManager.invokeSecureMethod(service, "deleteUser");

        // Try accessing with ADMIN role
        SecurityManager.currentUserRole = "ADMIN";
        SecurityManager.invokeSecureMethod(service, "deleteUser");
    }
}
