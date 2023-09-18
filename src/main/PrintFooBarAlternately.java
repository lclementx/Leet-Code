import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintFooBarAlternately {
    private AtomicInteger fooCounter = new AtomicInteger(0);
    private AtomicInteger barCounter = new AtomicInteger(0);
    private int n;

    public PrintFooBarAlternately(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            while (fooCounter.get() > barCounter.get()) {
                Thread.sleep(1);
            }
            printFoo.run();
            fooCounter.addAndGet(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            while (barCounter.get() != fooCounter.get() - 1) {
                Thread.sleep(1);
            }
            printBar.run();
            barCounter.addAndGet(1);
        }
    }
}
