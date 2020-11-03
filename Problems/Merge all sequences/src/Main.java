import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Main {

    private static int[] merge(int[] array1, int[] array2) {
        int[] newArray = new int[array2.length + array1.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < array1.length && j < array2.length) {
            if (array1[i] > array2[j]) {
                newArray[k++] = array1[i++];
            } else {
                newArray[k++] = array2[j++];
            }
        }

        while (i < array1.length) {
            newArray[k++] = array1[i++];
        }

        while (j < array2.length) {
            newArray[k++] = array2[j++];
        }

        return newArray;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<int[]> list = new ArrayList<>();
        for (int k = scanner.nextInt(); k > 0; k--) {
            int[] array = new int[scanner.nextInt()];
            for (int j = 0; j < array.length; j++) {
                array[j] = scanner.nextInt();
            }
            list.add(array);
        }

        while (list.size() > 1) {
            list.add(merge(list.remove(0), list.remove(0)));
        }

        list.stream().forEach(e -> {
            for (int i : e) {
                System.out.print(i + " ");
            }
        });
    }
}