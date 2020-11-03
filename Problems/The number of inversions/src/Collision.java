public class Collision {

    static int a = 3;
    static int mod = 11;

    static double pow(int num, int pow) {
        return Math.pow(num, pow);
    }

    private static int[] getCharValues(String word) {
        int[] charValues = new int[word.length()];
        for (int i = 0; i < charValues.length; i++) {
            charValues[i] = word.charAt(i) - 96;
        }
        return charValues;
    }

    private static int getHash(int[] charValues, int len) {
        int hash = 0;
        int j = len;
        for (int i = charValues.length - 1; i < charValues.length - len; i++, j--) {
            hash += charValues[i] * pow(a, j);
        }
        hash %= mod;
        return hash;
    }



    private static int getCollisions(String word, int len) {
        int collisions = 0;
        int[] charValues = getCharValues(word);

        int hash = getHash(charValues, len);
        int newHash;
        int wordLenght = charValues.length;
        for (int i = wordLenght - len - 1,
             j = wordLenght - 1; i >= 0; i--, j--) {
            newHash = (int) ((hash - charValues[j] * pow(a,wordLenght - i - 1)) * a + charValues[i]) % mod;
            if (newHash == hash) {
                collisions++;
            }
        }

        return collisions;
    }

    public static void main(String[] args) {

//        double hash1 = (1*pow(3,0) +
//                3*pow(3,1) +
//                3*pow(3,2) +
//                4*pow(3,3)) % 13;
//        System.out.println(hash1);
//
//        double hash2 = ((hash1 - 2*pow(3,4))*3 + 1) % 13;

        System.out.println(getCollisions("acacbad", 3));

    }

}
