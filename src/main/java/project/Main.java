package project;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new StatisticPrinter().run(args[0]);
        } catch (IOException e) {
            System.out.println("Failed to get statistics: " + e);
        }
    }
}
