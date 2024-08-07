package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.*;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean notContain = !contains(value);
        if (notContain) {
            set.add(value);
        }
        return notContain;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T e : set) {
            if (Objects.equals(e, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

class Test {
    public static void main(String[] args) {
        SimpleArraySet<Integer> s = new SimpleArraySet<>();
        s.add(1);
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);
        s.add(5);
        System.out.println(s.contains(1));
        System.out.println(s.contains(2));
        System.out.println(s.contains(3));
        System.out.println(s.contains(4));
        System.out.println(s.contains(5));
    }
}