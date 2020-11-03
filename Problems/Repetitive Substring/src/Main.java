import java.util.*;

public class Main {
    static String line;
    static int a = 135_719;
    static long m = 9_000_000_071L;

    private static void doJob(int length) {
        Hashtable<Long, Integer> subs = new Hashtable<>();
//        int start = line.length() - length;
        long pow = 1;
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += line.charAt(line.length() - length + i) * pow;
            hash %= m;

            if (i != length - 1) {
                pow = pow * a % m;
            }
        }

        subs.put(hash, line.length() - length);

        for (int i = line.length(); i > length; i--) {
            hash = (hash - line.charAt(i - 1) * pow % m + m) * a % m;
            hash = (hash + line.charAt(i - length - 1)) % m;
            if (subs.containsKey(hash)) {
                int pos = subs.get(hash);
//                System.out.println(line.substring(j, j + length));
//                System.out.println(line.substring(i - length, i));
                String substring = line.substring(pos, pos + length);
                if (line.substring(i - length - 1, i - 1).equals(substring)) {
                    System.out.println(substring);
                    return;
                }
            } else {
                subs.put(hash, i - length - 1);
            }
        }

        System.out.println("does not contain");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
        int length = scanner.nextInt();
        long now = System.nanoTime();
        doJob(length);
//        System.out.println((System.nanoTime() - now) / 1000000);
    }
}