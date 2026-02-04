package BridgeLab_Training.Json_Data;

import org.json.JSONObject;

public class MergeJsonObjects {

    public static void main(String[] args) {

        JSONObject json1 = new JSONObject();
        json1.put("name", "Ansh");
        json1.put("age", 21);

        JSONObject json2 = new JSONObject();
        json2.put("email", "ansh@gmail.com");
        json2.put("city", "Bangalore");

        // Merge json2 into json1
        for (String key : json2.keySet()) {
            json1.put(key, json2.get(key));
        }

        System.out.println(json1.toString(4)); // pretty print
    }
}
