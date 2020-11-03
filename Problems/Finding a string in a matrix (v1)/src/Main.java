import java.util.Scanner;

public class Main {

    static int x;
    static int y;
    static String matrix;
    static String pattern;
    static int[] indecies;

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


    private static void findIndecies(String pattern, String matrix) {
        if (matrix == null || matrix.length() == 0 || matrix.length() < pattern.length()) {
            System.out.println(0);
            return;
        }
        int count = 0;

        int patternLength = pattern.length();
        int[] prefix = getPrefix(pattern + '#' + matrix);
        for (int i = 2 * patternLength; i < prefix.length; i++) {
            if (prefix[i] == patternLength) {
                indecies[count++] = (i - 2 * patternLength) / y;
                indecies[count++] = (i - 2 * patternLength) % y;
            }
        }

        System.out.println(Math.round(count / 2.0));
        for (int i = 0; i < count - 1; i += 2) {
            System.out.println(indecies[i] + " " + indecies[i + 1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        pattern = sc.nextLine();

        x = sc.nextInt();
        y = sc.nextInt();
        sc.nextLine();

        StringBuilder preMatrix = new StringBuilder();

        for (int i = 0; i < x; i++) {
            preMatrix.append(sc.nextLine());
        }

        matrix = preMatrix.toString();
        indecies = new int[matrix.length()];

        findIndecies(pattern, matrix);


    }
}