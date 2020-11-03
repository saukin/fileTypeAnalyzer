import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main2 {

    static char[][] text;
    static char[][] pattern;
    static int[] prefix;

    private static int KMPSearch(int row) {
        /* 1 */
        int count = 0;


        int j = 0;
        /* 2 */
        outer: for (int i = 0; i < text[row].length; i++) {
            /* 3 */
            while (j > 0 && text[row][i] != pattern[0][j]) {
                j = prefix[j - 1];
            }
            /* 4 */
            if (text[row][i] == pattern[0][j]) {
                j += 1;
            }
            /* 5 */
            if (j == pattern[0].length) {

                for (int n = row + 1; n < row + pattern.length; n++) {
                    int a = 0;
                    for (int k = i - j + 1; k < i - j + pattern[0].length + 1; k++) {
                        if (text[n][k] != pattern[n - row][a++]) {
                            j = prefix[j - 1];
                            continue outer;
                        }
                    }
                }
                j = prefix[j - 1];
                count++;
            }
        }
        /* 6 */
        return count;
    }

    private static int[] prefix(char[] pattern) {
        int length = pattern.length;
        int[] prefix = new int[length];
        int j = 0;
        for (int i = 1; i < length; i++) {
            j = prefix[i - 1];
            while (j > 0 && pattern[i] != pattern[j]) {
                j = prefix[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                j++;
            }
            prefix[i] = j;
        }
        return prefix;
    }

    private static void findPattern(char[][] pattern, char[][] text) {
        prefix = prefix(pattern[0]);
        int count = 0;

        for (int i = 0; i < text.length - pattern.length + 1; i++) {
            count += KMPSearch(i);
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        long now = System.nanoTime();
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        sc.nextLine();

        String line;

        pattern = new char[x][y];
        for (int i = 0; i < x; i++) {
            line = sc.nextLine();
            for (int j = 0; j < y; j++) {
                pattern[i][j] = line.charAt(j);
            }
        }

        x = sc.nextInt();
        y = sc.nextInt();
        sc.nextLine();

        text = new char[x][y];
        for (int i = 0; i < x; i++) {
            line = sc.nextLine();
            for (int j = 0; j < y; j++) {
                text[i][j] = line.charAt(j);
            }
        }
        System.out.println(System.nanoTime() - now);
//        long now = System.nanoTime();
        findPattern(pattern, text);
        System.out.println(System.nanoTime() - now);
    }
}