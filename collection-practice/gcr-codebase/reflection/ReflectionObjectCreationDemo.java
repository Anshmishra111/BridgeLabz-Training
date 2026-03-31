package reflection;

import java.lang.reflect.Constructor;

class Student {

    private String name = "Unknown";
    private int age = 0;

    public Student() {}

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class ReflectionObjectCreationDemo {

    public static void main(String[] args) {

        try {
            Class<?> cls = Student.class;   // ✅ FIX HERE

            Constructor<?> ctor = cls.getDeclaredConstructor();
            Object obj = ctor.newInstance();

            Student s = (Student) obj;
            s.display();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
