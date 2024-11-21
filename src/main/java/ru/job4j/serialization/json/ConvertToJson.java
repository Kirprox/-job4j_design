package ru.job4j.serialization.json;

import org.json.JSONObject;

public class ConvertToJson {
    public static void main(String[] args) {
        Car car = new Car("Toyota", false, 240,
                new String[]{"air conditioning", "heated windshield"},
                new Engine(1.8));
        JSONObject jsonObject = new JSONObject(car);
        jsonObject.put("speed", car.getSpeed());
        jsonObject.put("model", car.getModel());
        jsonObject.put("isSportCar", car.isSportCar());
        jsonObject.put("subSystems", car.getSubSystems());
        jsonObject.put("engine", car.getEngine().getEngDisplacement());
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(car).toString());
    }
}
