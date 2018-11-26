package project;

import project.statistics.LanguageDistribution;
import project.statistics.LinesOfCode;
import project.statistics.NumberOfRepos;

import java.io.IOException;
import java.util.Map;

public class StatisticPrinter {

    public void run(String githubRootFolder) throws IOException {
        print("Starting to get statistics");
        print("Getting number of repos");
        long nrOfRepos = new NumberOfRepos().get(githubRootFolder);
        print("Number of repose: " + nrOfRepos);

        print("Getting lines of code");
        long linesOfCode = new LinesOfCode().get(githubRootFolder);
        print("Lines of code: " + linesOfCode);

        print("Getting distribution of languages");
        Map<String, Float> distribution = new LanguageDistribution().get(githubRootFolder);
        print("Distribution: " + distribution);
}

    public void print(String message) {
        System.out.println(message);
    }
}
