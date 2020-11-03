import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class EmptyFolders {

    private ConcurrentLinkedQueue<String> foldersList = new ConcurrentLinkedQueue<>();

    private void checkFolder(File file) {
        if (file.isFile()) {
            return;
        }
        File[] files = file.listFiles();
        if (files.length == 0) {
            foldersList.add(file.getName());
        } else {
            for (File f : files) {
                checkFolder(f);
            }
        }

    }

    private void perform() throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        File root = new File("e:\\STUDIES\\Hyperskill\\listfolders");
        for (File file : root.listFiles()) {
            executorService.submit(() -> checkFolder(file));
        }
        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.MILLISECONDS)) {

        }
        foldersList.stream().forEach(e -> System.out.print(e + " "));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        EmptyFolders ef = new EmptyFolders();
        ef.perform();
    }

}
