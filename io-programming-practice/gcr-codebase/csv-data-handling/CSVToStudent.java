package csv_data_handling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVToStudent {

    // Student class inside same file
    static class Student {
        int id;
        String name;
        int age;
        String course;

        Student(int id, String name, int age, String course) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.course = course;
        }

        @Override
        public String toString() {
            return "Student{id=" + id +
                   ", name='" + name + '\'' +
                   ", age=" + age +
                   ", course='" + course + '\'' +
                   '}';
        }
    }

    public static void main(String[] args) {

        String filePath = "students.csv"; // place file in project root
        List<Student> studentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Student student = new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        data[3]
                );

                studentList.add(student);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print all students
        for (Student s : studentList) {
            System.out.println(s);
        }
    }
}
