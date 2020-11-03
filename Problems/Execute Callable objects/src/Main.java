import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


class FutureUtils {

    static int count = 0;

    public static int executeCallableObjects(List<Future<Callable<Integer>>> items){
        int length = items.size();
        int sum = 0;

        for (int i = length - 1; i >= 0; i--) {
            try {
                sum += items.get(i).get().call();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sum;
    }

    public static void isPower(float num) {

        if (num == 1) {
            System.out.println("yes");
            return;
        } else if (num > 1 && num < 2) {
            System.out.println("no");
            return;
        } else {
            isPower(num / 2);
            System.out.println(++count);
        }

    }

    public static void main(String[] args) {
        isPower(10);
    }

}