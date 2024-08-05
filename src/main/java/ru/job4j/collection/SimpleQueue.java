package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int countElInput;
    private int countElOutput;

    public T poll() {
        if (countElOutput == 0) {
            while (countElInput != 0) {
                output.push(input.pop());
                countElOutput++;
                countElInput--;
            }
        }
        countElOutput--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        countElInput++;
    }
}