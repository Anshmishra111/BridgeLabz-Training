package BridgeLab_Training.Json_Data;

import java.io.BufferedReader;
import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadSpecificFields {

    public static void main(String[] args) {

        String filePath = "json-data/users.json";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            StringBuilder jsonData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                jsonData.append(line);
            }

            JSONArray users = new JSONArray(jsonData.toString());

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);

                String name = user.getString("name");
                String email = user.getString("email");

                System.out.println("Name: " + name + ", Email: " + email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
