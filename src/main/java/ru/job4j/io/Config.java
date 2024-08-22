package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().
                    filter(e -> e.contains("=") && !e.startsWith("#"))
                            .forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] pairs = output.toString().split(System.lineSeparator());
        for (String pair : pairs) {
            String[] str = pair.split("=", 2);
            if (str[0].isEmpty() || str[1].isEmpty()) {
                throw new IllegalArgumentException();
            }
            values.put(str[0], str[1]);
        }

    }

    public int getSizeValues() {
        return values.size();
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        new Config("data/app.properties").load();
    }

}
