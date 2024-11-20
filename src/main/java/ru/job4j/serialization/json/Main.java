package ru.job4j.serialization.json;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car("Toyota", false, 240,
                new String[] {"air conditioning", "heated windshield"}, new Engine(1.8));
        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(car);
        System.out.println(json);
        final Car convertedCar = gson.fromJson(json, Car.class);
        System.out.println(convertedCar);
    }
}
