package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        File input = new File(argsName.get("path"));
        File output = new File(argsName.get("out"));
        String delimiter = argsName.get("delimiter");
        String[] filter = argsName.get("filter").split(",");
        List<String> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            String[] headers = scanner.nextLine().split(delimiter);
            List<Integer> indexes = new ArrayList<>();
            StringBuilder headerRow = new StringBuilder();

            for (String column : filter) {
                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equalsIgnoreCase(column.trim())) {
                        indexes.add(i);
                        if (!headerRow.isEmpty()) {
                            headerRow.append(delimiter);
                        }
                        headerRow.append(headers[i]);
                        break;
                    }
                }
            }
            result.add(headerRow.toString());
            while (scanner.hasNext()) {
                String[] str = scanner.nextLine().split(delimiter);
                StringBuilder filteredStr = new StringBuilder();
                for (Integer index : indexes) {
                    if (!filteredStr.isEmpty()) {
                        filteredStr.append(delimiter);
                    }
                    filteredStr.append(str[index]);
                }
                result.add(filteredStr.toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if ("stdout".equals(output.toString())) {
            System.out.println(result);
        } else {
            try (PrintWriter outputWriter = new PrintWriter(new BufferedOutputStream(
                    new FileOutputStream(output)))) {
                for (String line : result) {
                    outputWriter.println(line);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        File input = new File(argsName.get("path"));
        File output = new File(argsName.get("out"));
        String delimiter = argsName.get("delimiter");
        if (!output.toString().equals("stdout") && !output.exists()) {
            throw new IllegalArgumentException("Illegal output argument!");
        }
        if (!input.exists()) {
            throw new IllegalArgumentException("Illegal input argument!");
        }

        if (!delimiter.matches("[,;]")) {
            throw new IllegalArgumentException("Illegal delimiter sign!");
        }
        handle(argsName);
    }
}