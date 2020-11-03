import java.util.Scanner;

public class Main {

    private static int getMaxPrefix(String word) {
        int max = 0;
        int[] prefix = new int[word.length()];
        int j;
        for (int i = 1; i < word.length(); i++) {
            j = prefix[i - 1];
            while (j > 0 && word.charAt(i) != word.charAt(j)) {
                j = prefix[j - 1];
            }
            if (word.charAt(i) == word.charAt(j)) {
                j++;
            }
            prefix[i] = j;
            if (prefix[i] > max) {
                max = prefix[i];
            }

        }
        return max;
    }

    private static int[] getPrefix(String word) {
//        word = word.toLowerCase();
        int[] prefix = new int[word.length()];
        int j;
        for (int i = 1; i < word.length(); i++) {
            j = prefix[i - 1];
            while (j > 0 && word.charAt(i) != word.charAt(j)) {
                j = prefix[j - 1];
            }
            if (word.charAt(i) == word.charAt(j)) {
                j++;
            }

            prefix[i] = j;

        }
        return prefix;
    }

    private static int getMax(int[] array) {
        if (array == null) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private static int solution(String word) {

        if (word == null || word.equals("")) {
            return 1;
        }
        int num = 1;
        int length = word.length();
        String sub = word;
        for (int i = length - 1; i >= 0; i--) {
            int max1 = getMax(getPrefix(sub));
//            int max = getMaxPrefix(sub);
//            System.out.println(max + "  --- " + max1);
            num += sub.length() - max1;
            sub = sub.substring(1);
        }

        return num;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        while (true) {
            String word = sc.nextLine();
            System.out.println(solution(word));
//        }
    }
}