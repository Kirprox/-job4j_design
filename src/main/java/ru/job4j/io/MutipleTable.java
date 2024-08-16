package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MutipleTable {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("data/multipleresult.txt")) {
            for (int i = 0; i < 10; i++) {
            fos.write((i + " * 1 = " + i).getBytes());
            fos.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
