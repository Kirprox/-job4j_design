package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        String key, value;
        for (var str : args) {
            String[] arguments = str.split("=", 2);
            if (arguments.length < 2) {
                throw new IllegalArgumentException("Error: This argument '"
                        + arguments[0] + "' does not contain an equal sign");
            }
            if (arguments[0].length() <= 1) {
                throw new IllegalArgumentException("Error: This argument '"
                        + arguments[0] + "=" + arguments[1] + "' does not contain a key");
            }
            if (!arguments[0].startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '"
                        + arguments[0] + "=" + arguments[1] + "' does not start with a '-' character");
            }
            if (arguments[1].isEmpty()) {
                throw new IllegalArgumentException("Error: This argument '"
                        + arguments[0] + "=' does not contain a value");
            }
            key = arguments[0].substring(1);
            value = arguments[1];
            values.put(key, value);
        }

    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}