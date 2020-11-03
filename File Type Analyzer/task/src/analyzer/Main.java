package analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private String folderName;
    private Map<Long, Patterns> patterns;
    private int longestPattern;
    private int shortestPattern;

    public Main(String folderName, String patternDB) throws FileNotFoundException {
        this.patterns = Utils.getPatternsHashes(patternDB);
        this.folderName = folderName;
        this.longestPattern = Utils.getLongestPattern();
        this.shortestPattern = Utils.getShortestPattern();
    }


    public void perform() throws IOException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        File folder = new File(folderName);
        if (!folder.isFile()) {
            for (File file : folder.listFiles()) {
                executorService.submit(new RabinKarp(patterns, file.getPath(), longestPattern, shortestPattern));
            }
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) {
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Main main = new Main(args[0], args[1]);

        main.perform();


    }

}
