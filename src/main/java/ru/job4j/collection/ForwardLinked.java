package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        modCount++;
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            this.head = newNode;
            size++;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        size++;
    }

    public void addFirst(T value) {
        modCount++;
        head = new Node<>(value, head);
        size++;
    }

    public T get(int index) {
        int currentIndex = 0;
        Node<T> temp = head;
        while (temp != null) {
            if (currentIndex == index) {
                return temp.item;
            }
            temp = temp.next;
            currentIndex++;
        }
        throw new IndexOutOfBoundsException();
    }

    public T deleteFirst() {
        modCount--;
        if (head == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        T firstItem = head.item;
        Node<T> newNode = head.next;
        head.item = null;
        head.next = null;
        head = newNode;
        size--;
        return firstItem;
    }

    public Iterator<T> iterator() {
        int expectedModCount = modCount;

        return new Iterator<T>() {
            private Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}