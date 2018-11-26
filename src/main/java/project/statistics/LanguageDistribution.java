package project.statistics;

import project.Constants;
import project.util.FileScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageDistribution {

    private Map<String, Long> counts = new HashMap<>();
    private Map<String, Float> distribution = new HashMap<>();

    public Map<String, Float> get(String githubRootFolder) throws IOException {
        List<Path> files = FileScanner.getInstance().getFiles(githubRootFolder, Constants.fileEndings);
        files.forEach(this::addLinesOfCode);
        long sum = counts.values().stream().mapToLong(i -> i).sum();
        for (Map.Entry<String, Long> e : counts.entrySet()) {
            distribution.put(e.getKey(), ((float) e.getValue() / sum));
        }
        return distribution;
    }

    private void addLinesOfCode(Path path) {
        try {
            String fileEnding = FileScanner.getInstance().getFileEnding(path);
            counts.merge(Constants.fileEndingsMapping.get(fileEnding), Files.lines(path).count() + 1, (a, b) -> a+b);
        } catch (IOException e) {
            System.out.println("Error reading file: " + path.toFile().getAbsolutePath());
        }
    }
}
