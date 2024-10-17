package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("Размер директории: %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            try {
                Path currentPath = Paths.get(subfile.getAbsolutePath());
                System.out.println(subfile.getName());
                FileVisitor visitor = new FileVisitor();
                Files.walkFileTree(currentPath, visitor);
                System.out.println(visitor.getSize());
                System.out.println("------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class FileVisitor extends SimpleFileVisitor<Path> {
    private long size;

    public long getSize() {
        return size;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        size += attrs.size();
        return FileVisitResult.CONTINUE;
    }

}

