package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int countElInput;
    private int countElOutput;

    public T poll() {
        if (countElOutput == 0 && countElInput == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (countElOutput == 0) {
            while (countElInput != 0) {
                output.push(input.pop());
                countElOutput++;
                countElInput--;
            }
        }
        if (countElOutput == 0 && countElInput == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        countElOutput--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        countElInput++;
    }
}