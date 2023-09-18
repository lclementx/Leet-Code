import java.util.concurrent.ArrayBlockingQueue;

public class PrintInOrder {
    private final ArrayBlockingQueue<Integer> abq;

    public PrintInOrder() {
        this.abq = new ArrayBlockingQueue<Integer>(3);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Dno not change or remove this line.
        printFirst.run();
        abq.add(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        while(!abq.contains(1) ) {
            Thread.sleep(1);
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        abq.add(2);

    }

    public void third(Runnable printThird) throws InterruptedException {
        abq.add(3);
        // printFirst.run() outputs "first". Do not change or remove this line.
        while(!abq.contains(2)) {
            Thread.sleep(1);
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
