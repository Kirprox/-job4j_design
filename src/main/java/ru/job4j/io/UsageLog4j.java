package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        long numberOfStudents = 150000L;
        short height = 180;
        byte weight = 80;
        double salary = 5000.0;
        float index = 14.4f;
        char sex = 'm';
        boolean transport = true;
        String favoritePlace = "forest";
        LOG.debug("User info name : {}, age : {}, students : {}, height : {}, weight : {}, "
                        + "salary : {}, index : {}, sex : {}, transport : {}, place : {}", name, age,
                numberOfStudents, height, weight, salary, index, sex, transport, favoritePlace);
    }
}