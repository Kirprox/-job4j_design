package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
public class Engine {
    @XmlAttribute
    double engDisplacement;

    public Engine(double engDisplacement) {
        this.engDisplacement = engDisplacement;
    }

    public Engine() {
    }

    @Override
    public String toString() {
        return "Engine{"
                + "engDisplacement=" + engDisplacement
                + '}';
    }

    public double getEngDisplacement() {
        return engDisplacement;
    }
}
