package BridgeLab_Training.Json_Data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

// Model class
class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters (required for Jackson)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// Main class
public class ListToJsonArray {

    public static void main(String[] args) {

        try {
            // Create list of Java objects
            List<Student> students = new ArrayList<>();
            students.add(new Student("Ansh", 21));
            students.add(new Student("Rahul", 22));
            students.add(new Student("Priya", 20));

            // Convert list to JSON array
            ObjectMapper mapper = new ObjectMapper();
            String jsonArray =
                    mapper.writerWithDefaultPrettyPrinter()
                          .writeValueAsString(students);

            // Print JSON array
            System.out.println(jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
