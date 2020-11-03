import java.util.Scanner;

public class Main {

    private static long a = 53;
    private static long m = 1_000_000_009;

    private static long[] textHashes;
    private  static String text;
    private static int intervalsCount;
    private static int[] indicies;


    private static long getlongValue(char c) {
        return c - 'A' + 1;
    }

    private static void getTextHashes() {
        textHashes = new long[text.length()];
        textHashes[0] = getlongValue(text.charAt(0)) % m;
        System.out.print(textHashes[0] + " ");
        long pow = a;
        for (int i = 1; i < text.length(); i++) {
            textHashes[i] = (textHashes[i - 1] + getlongValue(text.charAt(i)) * pow) ;
            pow *= a;
            if (i == text.length() - 1) {
                System.out.print(textHashes[i] % m);
            } else {
                long x = (textHashes[i]) % m;
                System.out.print(x + " ");
            }
        }
        System.out.println();
    }

    private static void getIntervalHashes() {
        for (int i = 0; i < indicies.length - 1; i++) {
            if (indicies[i + 1] > indicies[i]) {
                if (indicies[i] < 0) {
                    if (indicies[i + 1] >= 0) {
                        System.out.print(((textHashes[indicies[i + 1]] - 0) % m + m) % m + " ");
                    } else {
                        System.out.print(0 + " ");
                    }
                    i++;
                    continue;
                }
                if (i == indicies.length - 2) {
                    System.out.print(((textHashes[indicies[i + 1]] - textHashes[indicies[i++]]) % m + m) % m);
                } else {
                    System.out.print(((textHashes[indicies[i + 1]] - textHashes[indicies[i++]]) % m + m) % m + " ");
                }
            } else {
                System.out.print(0 + " ");
            }
        }
    }


    private static void doJob() {
        getTextHashes();
        getIntervalHashes();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        text = scanner.nextLine();

        intervalsCount = scanner.nextInt();

        indicies = new int[intervalsCount * 2];

        for (int i = 0; i < intervalsCount * 2; i++) {
            scanner.nextLine();
            indicies[i++] = scanner.nextInt() - 1;
            indicies[i] = scanner.nextInt() - 1;
        }
        scanner.nextLine();
        doJob();
    }
}