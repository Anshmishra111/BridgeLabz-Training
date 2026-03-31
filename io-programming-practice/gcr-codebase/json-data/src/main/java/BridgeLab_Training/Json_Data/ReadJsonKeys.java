package BridgeLab_Training.Json_Data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class ReadJsonKeys {

    public static void main(String[] args) {

        try {
            // JSON file from ANY location
            String filePath = "json-data/data.json";
            // OR relative path: "data/data.json"

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(filePath));

            // Print all keys and values
            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                System.out.println(
                        "Key: " + entry.getKey() +
                        ", Value: " + entry.getValue()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
