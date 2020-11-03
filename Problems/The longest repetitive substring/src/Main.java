import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static int a = 135919;
    static long m = 11_009_000_000_071L;
    static String line;

    private static boolean isRepeated(int length) {

        HashSet<Long> set = new HashSet<>();
        long pow = 1;
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += line.charAt(line.length() - length + i) * pow;
            hash %= m;
            if (i != length - 1) {
                pow = pow * a % m;
            }
        }

        set.add(hash);

        for (int i = line.length(); i > length; i--) {
            hash = (hash - line.charAt(i - 1) * pow % m + m) * a % m;
            hash = (hash + line.charAt(i - length - 1)) % m;
            if (!set.add(hash)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        line = scanner.nextLine();
        if (line == null || line.trim().length() == 0) {
            System.out.println(0);
            return;
        }

        int max = 0;
        int low = 0;
        int high = line.length() - 1;

        while (low <= high) {
            int length = (low + high) / 2;
            if (isRepeated(length)) {
                low = length + 1;
                max = length;
            } else {
                high = length - 1;
            }
        }

        System.out.println(max);

    }

}