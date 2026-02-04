package BridgeLab_Training.Json_Data;

import org.json.JSONObject;

class Car {
    private String brand;
    private String model;
    private int year;
    private double price;

    public Car(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    // Convert Car object to JSON
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("brand", brand);
        json.put("model", model);
        json.put("year", year);
        json.put("price", price);
        return json;
    }
}

public class CarToJson {

    public static void main(String[] args) {

        Car car = new Car("Toyota", "Camry", 2022, 2500000);

        JSONObject carJson = car.toJson();

        System.out.println(carJson.toString(4)); // pretty print
    }
}
