package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
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
                Files.walkFileTree(currentPath, new FileVisitor());
                System.out.println(FileVisitor.getSize());
                FileVisitor.setSize(0);
                System.out.println("------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class FileVisitor extends SimpleFileVisitor<Path> {
    private static long size;

    public static long getSize() {
        return size;
    }

    public static void setSize(long size) {
        FileVisitor.size = size;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        size += attrs.size();
        return FileVisitResult.CONTINUE;
    }

}

