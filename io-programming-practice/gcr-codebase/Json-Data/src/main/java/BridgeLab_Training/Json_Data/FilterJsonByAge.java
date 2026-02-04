package BridgeLab_Training.Json_Data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;

public class FilterJsonByAge {

    public static void main(String[] args) {

        try {
            // Sample JSON array (can also be read from file)
            String jsonData = """
            [
              {"name":"Ansh","age":21},
              {"name":"Rahul","age":28},
              {"name":"Priya","age":26},
              {"name":"Amit","age":24}
            ]
            """;

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(jsonData);

            System.out.println("Records where age > 25:\n");

            for (JsonNode node : rootArray) {
                if (node.get("age").asInt() > 25) {
                    System.out.println(
                        "Name: " + node.get("name").asText() +
                        ", Age: " + node.get("age").asInt()
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
