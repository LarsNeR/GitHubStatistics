package project.statistics;

import project.Constants;
import project.util.FileScanner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LinesOfCode {

    private long sum = 0;

    public long get(String githubRootFolder) throws IOException {
        List<Path> files = FileScanner.getInstance().getFiles(githubRootFolder, Constants.fileEndings);
        files.forEach(this::addLinesOfCode);
        return sum;
    }

    private void addLinesOfCode(Path path) {
        try {
            sum += Files.lines(path).count() + 1;
        } catch (IOException e) {
            System.out.println("Error reading file: " + path.toFile().getAbsolutePath());
        }
    }
}
