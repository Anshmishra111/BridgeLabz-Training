package BridgeLab_Training.Json_Data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ValidateJsonWithJackson {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            // Read JSON file
            JsonNode rootNode = mapper.readTree(new File("json-data/student.json"));

            // Validate required fields
            if (!rootNode.has("name")) {
                throw new RuntimeException("Missing field: name");
            }
            if (!rootNode.has("age")) {
                throw new RuntimeException("Missing field: age");
            }
            if (!rootNode.has("email")) {
                throw new RuntimeException("Missing field: email");
            }

            // Validate data types
            if (!rootNode.get("name").isTextual()) {
                throw new RuntimeException("Invalid type: name must be String");
            }
            if (!rootNode.get("age").isInt()) {
                throw new RuntimeException("Invalid type: age must be Integer");
            }
            if (!rootNode.get("email").isTextual()) {
                throw new RuntimeException("Invalid type: email must be String");
            }

            System.out.println("✅ JSON structure is valid");

        } catch (Exception e) {
            System.out.println("❌ JSON validation failed");
            System.out.println(e.getMessage());
        }
    }
}
