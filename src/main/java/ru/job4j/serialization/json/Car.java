package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    String model;
    boolean isSportCar;
    int speed;
    String[] subSystems;
    Engine engine;

    public Car(String model, boolean isSportCar, int speed, String[] subSystems, Engine engine) {
        this.model = model;
        this.isSportCar = isSportCar;
        this.speed = speed;
        this.subSystems = subSystems;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", isSportCar=" + isSportCar
                + ", speed=" + speed
                + ", subSystems=" + Arrays.toString(subSystems)
                + '}';
    }
}
