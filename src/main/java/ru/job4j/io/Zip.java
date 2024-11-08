package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream output =
                             new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateInput(Path root, String exclude, File target) {
        if (exclude == null || !root.toFile().exists()) {
            throw new IllegalArgumentException("Illegal input arguments!");
        }
        if (!target.toString().endsWith(".zip")) {
            throw new IllegalArgumentException("illegal target argument!");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        Path root = Path.of(argsName.get("d"));
        String exclude = argsName.get("e");
        File target = new File(argsName.get("o"));
        validateInput(root, exclude, target);
        List<Path> paths = Search.search(root, path ->
                !path.toFile().getName().endsWith(exclude));
        Zip zipProject = new Zip();
        zipProject.packFiles(paths, target);
    }
}