package ru.job4j.serialization.json;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;

import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    String model;
    @XmlAttribute
    boolean isSportCar;
    @XmlAttribute
    int speed;
    @XmlElementWrapper
    @XmlElement(name = "subSystem")
    String[] subSystems;
    Engine engine;

    public Car(String model, boolean isSportCar, int speed, String[] subSystems, Engine engine) {
        this.model = model;
        this.isSportCar = isSportCar;
        this.speed = speed;
        this.subSystems = subSystems;
        this.engine = engine;
    }

    public Car() {
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

    public static void main(String[] args) throws JAXBException {
        Car car = new Car("Toyota", false, 240,
                new String[]{"air conditioning", "heated windshield"},
                new Engine(1.8));
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
