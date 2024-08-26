package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        String line;
        boolean started = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            while ((line = reader.readLine()) != null) {
                String[] pair = line.split(" ");
                int condition = Integer.parseInt(pair[0]);
                if ((condition == 500 || condition == 400) && !started) {
                    output.print(pair[1] + ";");
                    started = true;
                } else if ((condition == 300 || condition == 200) && started) {
                    output.println(pair[1]);
                }
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