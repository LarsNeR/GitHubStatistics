package project.statistics;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class NumberOfRepos {

    public long get(String githubRootFolder) {
        File root = new File(githubRootFolder);
        File[] folders = root.listFiles(File::isDirectory);
        return Arrays.stream(Objects.requireNonNull(folders)).filter(this::containsGitFolder).count();
    }

    private boolean containsGitFolder(File file) {
        return Arrays.stream(Objects.requireNonNull(file.listFiles(File::isDirectory))).anyMatch(p -> p.getName().equals(".git"));
    }
}
