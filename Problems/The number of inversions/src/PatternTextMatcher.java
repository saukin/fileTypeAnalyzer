public class PatternTextMatcher {
    static int a = 3;
    static int mod = 11;

    static double pow(int num, int pow) {
        return Math.pow(num, pow);
    }

    private static int[] getCharValues(String word) {
        int[] charValues = new int[word.length()];
        for (int i = 0; i < charValues.length; i++) {
            charValues[i] = word.charAt(i) - 64;
        }
        return charValues;
    }

    private static int getHash(int[] charValues, int len) {
        int hash = 0;
        int j = len - 1;
        for (int i = charValues.length - 1; i > charValues.length - len - 1; i--, j--) {
            hash += charValues[i] * pow(a, j);
        }
        hash %= mod;
        return hash;
    }



    private static void getCollisions(String pattern, String word, int len) {

        int[] patternCharValues = getCharValues(pattern);
        int[] wordCharValues = getCharValues(word);

        int patternHash = getHash(patternCharValues, len);
        int wordHash = getHash(wordCharValues, len);

        int wordLenght = wordCharValues.length;

        int i = wordLenght - len,
            j = wordLenght - 1;



        do {
            int comparisons = 0;
            if (wordHash == patternHash) {
                for (int k = i, l = 0; k <= j; k++, l++) {
                    comparisons++;
                    if (l > patternCharValues.length || patternCharValues[l] != wordCharValues[k]) {
                        break;
                    }
                }
            }
            System.out.println(wordHash + " " + comparisons + " " + (comparisons == len ? 1 : 0));
            i--;
            double x = (wordHash - wordCharValues[j] * pow(a,len - 1)) * a;
            double d = x + wordCharValues[i];
            double w = (d % mod + mod) % mod;
            wordHash = (int) w;
            j--;

        } while (i >= 0);

    }

    public static void main(String[] args) {

        getCollisions("DDA","DDABCD", 3);
//        getCollisions("ABBC","ABBCCB", 4);
//        System.out.println('A' - 0);
//        System.out.println(13 % Math.abs(3));
    }
}
