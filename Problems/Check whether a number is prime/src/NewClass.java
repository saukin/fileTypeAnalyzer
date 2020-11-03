import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class NewClass {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exe = Executors.newFixedThreadPool(4);

        List<Callable<Integer>> callables = List.of(() -> 1000, () -> 2000, () -> 3000);
        Optional<Integer> x = exe.invokeAll(callables).stream().map(e -> {
            try {
                return e.get();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
            return 0;
        }).reduce((e, b) -> e + b);


        System.out.println(x.toString());
        exe.shutdown();
    }
}
