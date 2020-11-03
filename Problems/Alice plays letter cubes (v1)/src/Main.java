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

    private static int getOccurrencies(String newWord, String word) {
        int[] prefix = getPrefix(newWord);
        for (int i : prefix) {
            System.out.print(i + " ");
        }
        int wordLength = word.length();
        int newWordLength = newWord.length();
        outer: for (int i = 0; i + newWordLength <= wordLength; i++) {
            for (int j = i; j < i + newWordLength; j++) {
                if (word.charAt(j) != newWord.charAt((j - i) % newWordLength)) {
                    if (j > i) {
                        i += (j - i) - prefix[j - i - 1] - 1;
                    }
                    continue outer;
                }
            }
            return 1;
        }
        return -1;
    }

    private static int getDistinctNum(String newWord, String word) {
        String substring = "";
        int distinctNum = 0;
        int wordLength = word.length();
        int newWordLength = newWord.length();
        for (int i = 0; i < newWordLength && wordLength >= substring.length(); i++) {
            substring += newWord.charAt(i);
            if (getOccurrencies(substring, word) > 0) {
                continue;
            } else {
                distinctNum++;
            }
        }
        return distinctNum;
    }

    private static void process(String s, int value, String[] newLetters) {
        int[] distinctSubstring = new int[value];
        String newWord;
        for (int i = 0; i < newLetters.length; i++) {
            newWord = newLetters[i] + s;
            distinctSubstring[i] = getDistinctNum(newWord, s);
        }
        for (int i : distinctSubstring) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int i = sc.nextInt();
        sc.nextLine();
        String[] newLetters = sc.nextLine().split(" ");
        process(s, i, newLetters);
    }   
}