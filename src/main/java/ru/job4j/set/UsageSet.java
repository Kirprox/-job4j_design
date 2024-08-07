package ru.job4j.set;

import java.util.*;

public class UsageSet {
    public static Set<String> convertToUnique(String[] input) {
        Set<String> result = new HashSet<>(Arrays.asList(input));
        return result;
    }

    public static Set<String> mergeSets(Set<String> firstSet, Set<String> secondSet) {
        Set<String> result = new HashSet<>(firstSet);
        result.retainAll(secondSet);
        return result;
    }

    public static void main(String[] args) {
        Set<String> res = convertToUnique(new String[]{"привет", "как дела?", "пока", "как дела?"});
        Set<String> res2 = Set.of("добрый день", "привет", "добрый вечер", "пока");
        System.out.println(mergeSets(res, res2));
    }
}