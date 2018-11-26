package project.statistics;

import project.util.FileScanner;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class NumberOfRepos {

    public long get(String githubRootFolder) {
        File root = new File(githubRootFolder);
        File[] dirs = root.listFiles(File::isDirectory);
        return Arrays.stream(Objects.requireNonNull(dirs)).filter(dir -> FileScanner.getInstance().containsGitFolder(dir)).count();
    }
}
