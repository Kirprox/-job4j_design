package ru.job4j.serialization.json;

public class Engine {
    double engDisplacement;

    public Engine(double engDisplacement) {
        this.engDisplacement = engDisplacement;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "engDisplacement=" + engDisplacement
                + '}';
    }
}
