import java.util.Scanner;

public class Main {

    private static void getOccs(String pattern, String text) {
        if (pattern == null || pattern.equals("")) {
            System.out.println(1);
            System.out.println(0);
            return;
        }
        int count = 0;
        int[] occs = new int[text.length() / pattern.length() + 1];
        int index = -1;
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (text.charAt(i) == pattern.charAt(0)) {
                index = i;
                for (int j = 1; j < pattern.length(); j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        index = -1;
                    }
                }
                if (index != -1) {
                    occs[count++] = index;
                    i = index + pattern.length() - 1;
                }
            }
        }
        if (count == 0) {
            System.out.println(0);
        } else {
            System.out.println(count);
            for (int i = 0; i < count; i++) {
                System.out.print(occs[i] + " ");
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine();
        String text = sc.nextLine();
        getOccs(pattern, text);
    }
}