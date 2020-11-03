import java.util.*;
import java.util.concurrent.*;


class FutureUtils {

    public static int howManyIsDone(List<Future> items) {
        int count = 0;
        for (Future e : items) {
            if (e.isDone()) {
                count++;
            }
        };
        return count;
    }

}