import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class kjh {

    static String[] text;
    static String[] pattern;
    static int[] prefix;

    private static int KMPSearch(int row) {
        /* 1 */
        int count = 0;

        int textLength = text[row].length();
        int patternLength = pattern[0].length();
        int j = 0;
        /* 2 */
        outer: for (int i = 0; i < textLength; i++) {
            /* 3 */
            while (j > 0 && text[row].charAt(i) != pattern[0].charAt(j)) {
                j = prefix[j - 1];
            }
            /* 4 */
            if (text[row].charAt(i) == pattern[0].charAt(j)) {
                j += 1;
            }
            /* 5 */
            if (j == patternLength) {

                for (int n = row + 1; n < row + pattern.length; n++) {
                    int a = 0;
                    for (int k = i - j + 1; k < i - j + patternLength + 1; k++) {
                        if (text[n].charAt(k) != pattern[n - row].charAt(a++)) {
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

    private static int[] prefix(String pattern) {
        int length = pattern.length();
        int[] prefix = new int[length];
        int j = 0;
        for (int i = 1; i < length; i++) {
            j = prefix[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefix[i] = j;
        }
        return prefix;
    }

    private static void findPattern(String[] pattern, String[] text) {
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

        pattern = new String[x];
        for (int i = 0; i < x; i++) {
            pattern[i] = sc.nextLine();
        }

        x = sc.nextInt();
        y = sc.nextInt();
        sc.nextLine();

        text = new String[x];
        for (int i = 0; i < x; i++) {
            text[i] = sc.nextLine();
        }
        System.out.println(System.nanoTime() - now);
//        long now = System.nanoTime();
        findPattern(pattern, text);
        System.out.println(System.nanoTime() - now);
    }
}