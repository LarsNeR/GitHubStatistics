package project.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileScanner {

    private static FileScanner instance;

    public static FileScanner getInstance() {
        if (instance == null) {
            instance = new FileScanner();
        }
        return instance;
    }

    private List<Path> files;

    public List<Path> getFiles(String githubRootFolder, List<String> fileEndings) throws IOException {
        if (files != null) {
            return files;
        }
        List<Path> srcFolders = Files.find(Paths.get(githubRootFolder), 2, ((path, basicFileAttributes) -> path.getFileName().toString().equals("src"))).collect(Collectors.toList());
        List<Path> rootFolders = Files.list(Paths.get(githubRootFolder)).filter(Files::isDirectory).collect(Collectors.toList());
        files = new ArrayList<>();

        srcFolders.forEach(src -> {
            try {
                files.addAll(Files.walk(src).filter(Files::isRegularFile).filter(f -> fileEndings.contains(getFileEnding(f))).collect(Collectors.toList()));
            } catch (IOException e) {
                System.out.println("Failed to get files from: " + src);
            }
        });

        rootFolders.forEach(root -> {
            try {
                files.addAll(Files.list(root).filter(Files::isRegularFile).filter(f -> fileEndings.contains(getFileEnding(f))).collect(Collectors.toList()));
            } catch (IOException e) {
                System.out.println("Failed to get files from: " + root);
            }
        });
        return files;
    }

    public String getFileEnding(Path path) {
        String fileName = path.getFileName().toString();
        int i = fileName.lastIndexOf(".");
        if (i > 0) {
            return fileName.substring(i+1);
        }
        return "";
    }
}
