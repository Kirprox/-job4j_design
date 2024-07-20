package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void grow() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 10);
        }
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T setElement = container[index];
        container[index] = newValue;
        return setElement;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        T removedElement = container[index];
        if (index < size - 1) {
            System.arraycopy(container, index + 1, container, index, size - index - 1);
        }
        container[--size] = null;
        return removedElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;

        return new Iterator<T>() {
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }

    public static void main(String[] args) {
        SimpleArrayList<Integer> s = new SimpleArrayList<>(5);
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);
        Iterator<Integer> iterator = s.iterator();
        s.add(5);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}