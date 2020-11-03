import java.util.Scanner;

public class Main {

    private static int[] getPrefix(String word) {

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

    private static void getOccs(String pattern, String text) {
        if (pattern == null || pattern.equals("")) {
            System.out.println(1);
            System.out.println(0);
            return;
        }
        int num = 0;
        int textLength = text.length();
        int patternLength = pattern.length();
        int[] indecies = new int[textLength];
        int[] prefix = getPrefix(pattern + "#" + text);

        for (int j = patternLength + 1; j < patternLength + 1 + textLength; j++) {
            if (prefix[j] == patternLength) {
                if (prefix[j - patternLength + 1] == 1) {
                    indecies[num++] = j - 2 * patternLength;
                    continue;
                } else if (indecies[num - 1] < j - 2 * (patternLength + 1)) {
                    indecies[num++] = j - 2 * patternLength;
                }
            }
        }

        System.out.println(num);
        for (int i = 0; i < num; i++) {
            System.out.print(indecies[i] + " ");
        }
    }


    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String pattern = sc.nextLine();
////        while (true) {
//            String text = sc.nextLine();
//            getOccs(pattern, text);
////        }
        System.out.println(13%4);
    }
}