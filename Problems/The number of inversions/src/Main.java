import java.util.Scanner;

class Main {

    static long count = 0;

    private static void mergeCount(long[] array) {
        mergeSort(array, 0, array.length);
    }

    private static void mergeSort(long[] array, int leftIncl, int rightExcl) {
        // the base case: if subarray contains <= 1 items, stop dividing because it's sorted
        if (rightExcl <= leftIncl + 1) {
            return;
        }

        /* divide: calculate the index of the middle element */
        int middle = leftIncl + (rightExcl - leftIncl) / 2;

        mergeSort(array, leftIncl, middle);  // conquer: sort the left subarray
        mergeSort(array, middle, rightExcl); // conquer: sort the right subarray

        /* combine: merge both sorted subarrays into sorted one */
        merge(array, leftIncl, middle, rightExcl);
//        count++;
    }

    private static void merge(long[] array, int left, int middle, int right) {
        int i = left;   // index for the left subarray
        int j = middle; // index for the right subarray
        int k = 0;      // index for the temp subarray

        long[] temp = new long[right - left]; // temporary array for merging

    /* get the next lesser element from one of two subarrays
       and then insert it in the array until one of the subarrays is empty */
        while (i < middle && j < right) {
            if (array[i] <= array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
                count += middle - i;
            }
            k++;
        }

        /* insert all the remaining elements of the left subarray in the array */
        for (;i < middle; i++, k++) {
            temp[k] = array[i];

        }

        /* insert all the remaining elements of the right subarray in the array */
        for (;j < right; j++, k++) {
            temp[k] = array[j];

        }

        /* effective copying elements from temp to array */
        System.arraycopy(temp, 0, array, left, temp.length);
    }

    private static int simpleCount(long[] array) {
        int count = 0;
        long now = System.nanoTime();
        for (int i = 0; i < array.length - 1; i++) {
            int j = i + 1;
            while (j < array.length) {
                if (array[i] > array[j++]) {
                    count++;
                }
            }
        }
//        System.out.println((System.nanoTime() - now) / 1000000.0);
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] array = new long[scanner.nextInt()];
        scanner.nextLine();

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextLong();
        }

//        System.out.println(simpleCount(array));
        mergeCount(array);
        System.out.println(count);
    }
}