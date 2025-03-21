package ru.job4j.gc.ref;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {
    public static void main(String[] args) {
        WeakReference<Object> weakRef = new WeakReference<>(new Object());
        Object strongRef = weakRef.get();
        System.gc();
        if (strongRef != null) {
            System.out.println("Объект еще жив");
        } else {
            System.out.println("Объект удален GC");
        }
    }
}