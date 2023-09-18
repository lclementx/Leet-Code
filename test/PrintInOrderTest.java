import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.*;

class PrintInOrderTest {

    @Test
    void PrintInOrderTestThreads() {
        PrintInOrder pio = new PrintInOrder();
        Runnable printFirst = () -> System.out.print("first");
        Runnable printSecond = () -> System.out.print("second");
        Runnable printThird = () -> System.out.print("third");
        Thread t1 = new Thread(() -> {
            try {
                pio.first(printFirst);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        Thread t2 = new Thread(() -> {
            try {
                pio.second(printSecond);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                pio.third(printThird);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t2.start();
        t3.start();
        t1.start();
    }

}