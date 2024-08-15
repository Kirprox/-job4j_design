package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int countAdd = 0;
        int countDelete = 0;
        int countChange = 0;
        Map<Integer, String> mapPrevious = new HashMap<>();
        for (User user : previous) {
            mapPrevious.put(user.getId(), user.getName());
        }
        for (User user : current) {
            if (!mapPrevious.containsKey(user.getId())) {
                countAdd++;
            }
            if (mapPrevious.containsKey(user.getId())
                    && !mapPrevious.containsValue(user.getName())) {
                countChange++;
            }
            mapPrevious.remove(user.getId());
        }
        countDelete = mapPrevious.size();
        return new Info(countAdd, countChange, countDelete);
    }

}