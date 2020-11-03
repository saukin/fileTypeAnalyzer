import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeepestFolder {

    private int maxDepth = 0;
    private String deepestFolder = "";


    class GetDeep implements Runnable {

        File folder;
        int depth;

        public GetDeep(File folder, int depth) {
            this.folder = folder;
            this.depth = depth;
        }

        @Override
        public void run() {
            checkFolder(folder, depth);
        }
    }

    private void checkFolder(File folder, int currentDepth) {
//        synchronized (this) {
            if (maxDepth < currentDepth) {
                maxDepth = currentDepth;
                deepestFolder = folder.getName();
            }
//        }

        if (folder.isFile()) {
            return;
        }

        for (File file : folder.listFiles()) {
            checkFolder(file, currentDepth + 1);
        }
    }

    private void perform() throws InterruptedException {
        long now = System.nanoTime();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        File currentFolder = new File("e:\\STUDIES\\Hyperskill\\ad");
        File[] innerFolders = currentFolder.listFiles();
        for (File file : innerFolders) {
            executorService.submit(new GetDeep(file, 1));
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) {

        }

        System.out.println((System.nanoTime() - now)/1000000000.0);
        System.out.println(deepestFolder + "  " + maxDepth);

    }

    public static void main(String[] args) throws InterruptedException {
        DeepestFolder df = new DeepestFolder();
        df.perform();

    }
}
