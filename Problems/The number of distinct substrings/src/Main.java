import java.util.*;

public class Main {
    static Set<Long> set = new HashSet<>();
    static String line;
    /* 1 */
    public static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }

    public static void rabinKarp(String text, String pattern) {
        /* 2 */
        int a = 53;
        long m = 1_000_000_000 + 9;

        /* 3 */
        long patternHash = 0;
        long currSubstrHash = 0;
        long pow = 1;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash += charToLong(pattern.charAt(i)) * pow;
            patternHash %= m;

            currSubstrHash += charToLong(text.charAt(text.length() - pattern.length() + i)) * pow;
            currSubstrHash %= m;

            if (i != pattern.length() - 1) {
                pow = pow * a % m;
            }
        }

        /* 4 */
        set.add(patternHash);

        for (int i = text.length(); i >= pattern.length(); i--) {
            if (patternHash != currSubstrHash) {
                set.add(currSubstrHash);
            }

            if (i > pattern.length()) {
                /* 5 */
                currSubstrHash = (currSubstrHash - charToLong(text.charAt(i - 1)) * pow % m + m) * a % m;
                currSubstrHash = (currSubstrHash + charToLong(text.charAt(i - pattern.length() - 1))) % m;
            }
        }
    }


    private static void doJob() {
        String subString;
        for (int i = 1; i <= line.length(); i++) {
            subString = line.substring(0, i);
            rabinKarp(line, subString);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
        doJob();
        System.out.println(set.size() + 1);
    }
}