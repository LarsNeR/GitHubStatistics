package project.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        files = new ArrayList<>();
        List<Path> rootFolders = Files.list(Paths.get(githubRootFolder)).filter(Files::isDirectory).filter(dir -> FileScanner.getInstance().containsGitFolder(dir.toFile())).collect(Collectors.toList());
        List<Path> srcFolders = new ArrayList<>();

        rootFolders.forEach(gitFolder -> {
            try {
                srcFolders.addAll(Files.find(gitFolder, 2, ((path, basicFileAttributes) -> path.getFileName().toString().equals("src"))).collect(Collectors.toList()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


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

    public boolean containsGitFolder(File file) {
        return Arrays.stream(Objects.requireNonNull(file.listFiles(File::isDirectory))).anyMatch(p -> p.getName().equals(".git"));
    }
}
