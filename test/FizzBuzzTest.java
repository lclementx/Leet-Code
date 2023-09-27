import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.IntConsumer;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    private Runnable tryCatchRunnable(FizzBuzz object, String methodName, IntConsumer consumer) {
        Runnable runnableWithTryCatch = () -> {
            try {
                Method method = FizzBuzz.class.getMethod(methodName, IntConsumer.class);
                method.invoke(object, consumer);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
        return runnableWithTryCatch;
    };

    private Runnable tryCatchRunnable(FizzBuzz object, String methodName, Runnable runnable) {
        Runnable runnableWithTryCatch = () -> {
            try {
                Method method = FizzBuzz.class.getMethod(methodName, Runnable.class);
                method.invoke(object, runnable);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
        return runnableWithTryCatch;
    };

    @Test
    void TestFizzBuzz() throws ExecutionException, InterruptedException {
        FizzBuzz fb = new FizzBuzz(15);
        IntConsumer printer = System.out::println;
        Runnable numberRunnable = tryCatchRunnable(fb,"number",printer);
        Runnable fizzPrinter = tryCatchRunnable(fb,"fizz",() -> System.out.println("fizz"));
        Runnable buzzPrinter = tryCatchRunnable(fb,"buzz",() -> System.out.println("buzz"));
        Runnable fizzBuzzPrinter = tryCatchRunnable(fb,"fizzbuzz",() -> System.out.println("fizzbuzz"));

        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture cf1 = CompletableFuture.runAsync(() -> fizzPrinter.run(),executorService);
        CompletableFuture cf2 = CompletableFuture.runAsync(() -> buzzPrinter.run(),executorService);
        CompletableFuture cf3 = CompletableFuture.runAsync(() -> fizzBuzzPrinter.run(),executorService);
        CompletableFuture cf4 = CompletableFuture.runAsync(() -> numberRunnable.run(),executorService);

        CompletableFuture cf = CompletableFuture.allOf(cf4,cf3,cf2,cf1);
        cf.get();
    }
}