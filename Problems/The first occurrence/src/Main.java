import java.util.Scanner;

public class Main {

    private static int getIndex(String pattern, String text) {
        if (pattern == null || pattern.equals("")) {
            return 0;
        }
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
                    return index;
                }
            }
        }

        return index;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine();
        String text = sc.nextLine();
        System.out.println(getIndex(pattern, text));
    }
}