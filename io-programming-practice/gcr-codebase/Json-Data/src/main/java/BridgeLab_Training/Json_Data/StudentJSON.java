package BridgeLab_Training.Json_Data;

import org.json.JSONArray;
import org.json.JSONObject;

public class StudentJSON {

    // Method to create Student JSON
    public static JSONObject createStudentJson() {

        JSONObject student = new JSONObject();
        student.put("name", "Ansh");
        student.put("age", 21);

        JSONArray subjects = new JSONArray();
        subjects.put("Maths");
        subjects.put("Computer Science");
        subjects.put("Physics");

        student.put("subjects", subjects);

        return student;
    }

    // Main method
    public static void main(String[] args) {

        JSONObject studentJson = createStudentJson();
        System.out.println(studentJson.toString(4)); // pretty print
    }
}






