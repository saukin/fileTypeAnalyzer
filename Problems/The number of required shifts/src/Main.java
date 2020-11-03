import java.util.Scanner;

class Main {

    private static int countShifts(int[] array) {
        int count = 0;

        for (int i = 1; i < array.length; i++) {
            int num = array[i];
            int j = i - 1;
            if (array[j] < array[i]) {
                count++;
            }
            while (j >= 0 && array[j] < num) {
                array[j + 1] = array[j];
                j--;

            }
            array[j + 1] = num;

        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[scanner.nextInt()];
        scanner.nextLine();
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println(countShifts(array));
    }
}