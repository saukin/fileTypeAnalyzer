import java.util.Scanner;

public class Main {

    private static long[] powArray;
    private static long[] lineHashes;
    private static long a = 53;
    private static long m = 1_000_000_009;

    private static void getHashes(String line) {
        int length = line.length();
        lineHashes = new long[length + 1];
        powArray = new long[length];
        powArray[0] = 1;
        for (int i = 0; i < length; i++) {
            lineHashes[i + 1] = (lineHashes[i] + line.charAt(i) * powArray[i]) % m;
            if (i < length - 1) {
                powArray[i + 1] = powArray[i] * a % m;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();

            getHashes(line);

            int rows = scanner.nextInt();
            scanner.nextLine();

            int count = 0;
            long hash1;
            long hash2;

            for (int l = 0; l < rows; l++) {
                int i = scanner.nextInt();
                int j = scanner.nextInt();
                int k = scanner.nextInt();
                int n = scanner.nextInt();
                if (i <= k) {
                    hash1 = (lineHashes[j] - lineHashes[i] + m) * powArray[k - i] % m;
                    hash2 = (lineHashes[n] - lineHashes[k] + m) % m;
                } else {
                    hash1 = (lineHashes[j] - lineHashes[i] + m) % m;
                    hash2 = (lineHashes[n] - lineHashes[k] + m) * powArray[i - k] % m;
                }
                if (hash1 == hash2) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}