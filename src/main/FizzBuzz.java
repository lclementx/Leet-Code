import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;

    private AtomicInteger storedCount = new AtomicInteger(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(storedCount.getAcquire() <= n){
            int count = storedCount.getAcquire();
            if (count % 3 == 0 & count % 5 != 0 & count <= n) {
                printFizz.run();
                storedCount.getAndIncrement();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(storedCount.getAcquire() <= n){
            int count = storedCount.getAcquire();
            if (count % 5 == 0 & count % 3 != 0 & count <= n) {
                printBuzz.run();
                storedCount.setRelease(count + 1);
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(storedCount.getAcquire() <= n){
            int count = storedCount.getAcquire();
            if (count % 3 == 0 & count % 5 == 0 & count <= n) {
                printFizzBuzz.run();
                storedCount.setRelease(count + 1);            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(storedCount.getAcquire() <= n){
            int count = storedCount.getAcquire();
            if (count % 3 != 0 & count % 5 != 0 & count <= n) {
                printNumber.accept(count);
                storedCount.setRelease(count + 1);
            }
        }
    }
}
