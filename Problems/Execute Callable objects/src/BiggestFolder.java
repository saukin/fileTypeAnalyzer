import java.io.File;

public class BiggestFolder {

    private static String maxFolder;
    private static int maxCount = 0;
    private static int folderCount;

    private static void perform() {
        File currentFolder = new File("e:\\STUDIES\\Hyperskill\\basedir");
        File[] innerFolders = currentFolder.listFiles();
        for (File folder : innerFolders) {
            folderCount = folder.listFiles().length;
            if (folderCount > maxCount) {
                maxFolder = folder.getName();
                maxCount = folderCount;
            }
        }
        System.out.printf("\"%s\" %d", maxFolder, maxCount);
    }

    public static void main(String[] args) {

        perform();

    }

}
