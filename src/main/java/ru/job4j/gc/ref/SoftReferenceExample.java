package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;

public class SoftReferenceExample {
    private static SoftReference<Object> softRef = new SoftReference<>(new Object());

    public static void main(String[] args) {
        Object obj = softRef.get();
        if (obj == null) {
            obj = new Object();
            softRef = new SoftReference<>(obj);
            System.out.println("Объект пересоздан");
        } else {
            System.out.println("Объект доступен");
        }
    }
}
