import java.util.Hashtable;
import java.util.Scanner;

public class Main2 {

    private static final int A = 135_719;
    private static final long M = 9_000_000_071L;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long now = System.nanoTime();
        System.out.println(twiceInText(scanner.nextLine(), scanner.nextInt()));
        System.out.println((System.nanoTime() - now) / 1000000);
    }

    private static String twiceInText(String text, int length) {
        //HashSet<Long> subStringHashes = new HashSet<>();
        Hashtable<Long, Integer> subs = new Hashtable<>();
        long currHash = 0;
        long pow = 1;

        for (int i = 0; i < length; i++) {
            currHash += text.charAt(text.length() - length + i) * pow;
            currHash %= M;
            if (i != length - 1) {
                pow = pow * A % M;
            }
        }
        //subStringHashes.add(currHash);
        subs.put(currHash, text.length() - length);

        for (int i = text.length(); i > length; i--) {
            currHash = (currHash - text.charAt(i - 1) * pow % M + M) * A % M;
            currHash = (currHash + text.charAt(i - length - 1)) % M;

            if (subs.containsKey(currHash)) {
                int pos = subs.get(currHash);
                String subStr = text.substring(pos, pos + length);
                if (text.substring(i - length - 1, i - 1).equals(subStr)) {
                    return subStr;
                }
            } else {
                subs.put(currHash, i - length - 1);
            }

//            if (subStringHashes.contains(currHash)) {
//                return text.substring(i - length - 1, i - 1);
//            } else {
//                subStringHashes.add(currHash);
//            }
        }
        return "does not contain";
    }
}
