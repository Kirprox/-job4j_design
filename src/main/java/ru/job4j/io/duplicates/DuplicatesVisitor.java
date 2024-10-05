package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> map = new HashMap<>();
    private List<Path> duplFiles = new ArrayList<>();

    public void printDuplFiles() {
        for (Path path : duplFiles) {
            System.out.println(path.toString());
        }
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        Path currentPath = file.toAbsolutePath();
        FileProperty currentFileProperty = new FileProperty(attributes.size(),
                currentPath.getFileName().toString());
        Path result = map.putIfAbsent(currentFileProperty, currentPath);
        if (result != null) {
            duplFiles.add(map.get(currentFileProperty));
            duplFiles.add(file.toAbsolutePath());
            duplFiles.add(Paths.get("--------------------"));

        }
        return super.visitFile(file, attributes);
    }

}