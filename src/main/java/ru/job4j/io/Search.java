package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Arguments length not equal 2");
        }
        Path start = Paths.get(args[0]);
        if (!Files.isDirectory(start) || !Files.exists(start)) {
            throw new IllegalArgumentException("root folder does not exist");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("not a file");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

}