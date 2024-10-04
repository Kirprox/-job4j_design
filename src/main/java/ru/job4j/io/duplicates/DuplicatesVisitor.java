package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, Path> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        Path currentPath = file.toAbsolutePath();
        FileProperty currentFileProperty = new FileProperty(attributes.size(),
                currentPath.getFileName().toString());
        Path result = map.putIfAbsent(currentFileProperty, currentPath);
        if (result != null) {
            System.out.println(map.get(currentFileProperty));
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attributes);
    }

}