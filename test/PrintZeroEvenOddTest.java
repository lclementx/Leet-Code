import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;
import java.util.function.IntConsumer;

import static org.junit.jupiter.api.Assertions.*;

class PrintZeroEvenOddTest {

    private Runnable tryCatchRunnable(PrintZeroEvenOdd object, String methodName, IntConsumer consumer) {
        Runnable runnableWithTryCatch = () -> {
            try {
                Method method = PrintZeroEvenOdd.class.getMethod(methodName, IntConsumer.class);
                method.invoke(object, consumer);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
        return runnableWithTryCatch;
    };

    @Test
    void PrintZeroEvenOddTest_BaseCase() throws ExecutionException, InterruptedException, TimeoutException {
        PrintZeroEvenOdd pzeo = new PrintZeroEvenOdd(5);
        IntConsumer printer = System.out::println;
        Runnable zeroRunnable = tryCatchRunnable(pzeo,"zero",printer);
        Runnable evenRunnable = tryCatchRunnable(pzeo,"even",printer);
        Runnable oddRunnable = tryCatchRunnable(pzeo,"odd",printer);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(oddRunnable);
//        executorService.execute(zeroRunnable);
//        executorService.execute(evenRunnable);

        CompletableFuture cf1 = CompletableFuture.runAsync(() -> zeroRunnable.run(),executorService);
        CompletableFuture cf2 = CompletableFuture.runAsync(() -> evenRunnable.run(),executorService);
        CompletableFuture cf3 = CompletableFuture.runAsync(() -> oddRunnable.run(),executorService);

        CompletableFuture cf = CompletableFuture.allOf(cf3,cf2,cf1);
        cf.get();

    }
}