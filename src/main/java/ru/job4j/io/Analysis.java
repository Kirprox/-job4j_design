package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> input = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            input = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder resultBuilder = new StringBuilder();
        boolean started = false;
        for (String str : input) {
            String[] pair = str.split(" ");
            int condition = Integer.parseInt(pair[0]);
            if ((condition == 500 || condition == 400) && !started) {
                resultBuilder.append(pair[1] + ";");
                started = true;
            } else if ((condition == 300 || condition == 200) && started) {
                resultBuilder.append(pair[1] + "\n");
                started = false;
            }
        }
        String[] result = resultBuilder.toString().split(System.lineSeparator());
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (String str : result) {
                output.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}