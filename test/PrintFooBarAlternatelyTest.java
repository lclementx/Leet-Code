import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintFooBarAlternatelyTest {

    @Test
    void PrintFooBarAlternately() {
        PrintFooBarAlternately pfoa = new PrintFooBarAlternately(10);
        Thread t1 = new Thread(() -> {
            try {
                pfoa.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                pfoa.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
    }
}