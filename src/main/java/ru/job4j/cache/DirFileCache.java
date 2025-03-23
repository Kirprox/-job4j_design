package ru.job4j.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String dir = cachingDir + "\\" + key;
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(dir))) {
            while (scanner.hasNext()) {
                result.append(scanner.nextLine()).append("\n");
            }
            put(dir, result.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}