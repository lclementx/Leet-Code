import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;


public class PrintZeroEvenOdd {
    private AtomicInteger count = new AtomicInteger(1);

    private AtomicBoolean isFinished = new AtomicBoolean(false);

    private Semaphore zeroSemaphore = new Semaphore(1);
    private Semaphore oddSemaphore = new Semaphore(0);
    private Semaphore evenSemaphore = new Semaphore(0);

    private int n;

    public PrintZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(!isFinished.getAcquire()){
            zeroSemaphore.acquire();
            if(count.get() <= n){
                printNumber.accept(0);
                if(count.get() % 2 == 0){
                    evenSemaphore.release();
                }else{
                    oddSemaphore.release();
                }
            }else{
                isFinished.setRelease(true);
                evenSemaphore.release();
                oddSemaphore.release();
                break;
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while(!isFinished.getAcquire()){
            evenSemaphore.acquire();
            if(count.get() <= n){
                printNumber.accept(count.getAndIncrement());
                zeroSemaphore.release();
            }else{
                isFinished.setRelease(true);
                break;
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while(!isFinished.getAcquire()){
            oddSemaphore.acquire();
            if(count.get() <= n){
                printNumber.accept(count.getAndIncrement());
                zeroSemaphore.release();
            }else{
                isFinished.setRelease(true);
                break;
            }
        }
    }
}
