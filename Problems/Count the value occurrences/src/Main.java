import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        int count = 0;

        for (int i : list1) {
            if (i == elem) {
                count++;
            }
        }
        for (int i : list2) {
            if (i == elem) {
                count--;
            }
        }

        return count == 0;
    }
}