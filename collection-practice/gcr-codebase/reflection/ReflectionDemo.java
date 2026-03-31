package reflection;
import java.lang.reflect.*;
public class ReflectionDemo {
public static void main(String[] args) {
	try {
		String className="java.util.ArrayList";
		Class<?> cls=Class.forName(className);
		System.out.println("class name" + cls.getName());
		//constructors
		System.out.print("\nconstructors:");
		Constructor<?>[] constructors=cls.getDeclaredConstructors();
		for(Constructor<?> c:constructors) {
			System.out.println(c);
		}
		// -------- Fields --------
        System.out.println("\nFields:");
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        // -------- Methods --------
        System.out.println("\nMethods:");
        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

    } catch (ClassNotFoundException e) {
        System.out.println("Class not found!");
    }
}
}

