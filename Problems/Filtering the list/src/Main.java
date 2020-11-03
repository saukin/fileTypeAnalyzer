import java.util.*;


class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] array = sc.nextLine().split(" ");
        for (int i = array.length - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.print(Integer.parseInt(array[i]) + " ");
        }
    }
}