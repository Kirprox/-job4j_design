package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    ListIterator<T> iterator;

    public CyclicIterator(List<T> data) {
        this.data = data;
        this.iterator = data.listIterator();
    }

    @Override
    public boolean hasNext() {
        if (!iterator.hasNext()) {
            while (iterator.hasPrevious()) {
                iterator.previous();
            }
        }
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }
}