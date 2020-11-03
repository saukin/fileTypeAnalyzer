import java.util.concurrent.*;


class FutureUtils {

    public static int determineCallableDepth(Callable callable) throws Exception {

        Object object = callable.call();
        int count = 1;

        while (object instanceof Callable) {
            object = ((Callable<?>) object).call();
            count++;
        }

        return count;
    }

}