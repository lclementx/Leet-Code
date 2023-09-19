import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.IntConsumer;
import java.util.function.UnaryOperator;


public class PrintZeroEvenOdd {
    private AtomicReference<String> output = new AtomicReference<>("");
    private AtomicInteger even = new AtomicInteger(2);
    private AtomicInteger odd = new AtomicInteger(1);

    private int n;

    public PrintZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(output.get().length() < 2 * n){
            String currentString = output.get();
            if(!currentString.endsWith("0")){
                output.getAndUpdate((x) -> currentString.concat("0"));
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while(output.get().length() < 2 * n){
            if(even.get() < odd.get() & output.get().endsWith("0")){
                output.getAndUpdate((x) -> x.concat(String.valueOf(even.get())));
                even.getAndUpdate((x) -> x + 2);
            }
        }
        if(output.get().length() == 2*n){
            System.out.println(output.get());
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while(output.get().length() < 2 * n){
            if(odd.get() < even.get() & output.get().endsWith("0")){
                output.getAndUpdate((x) -> x.concat(String.valueOf(odd.get())));
                odd.getAndUpdate((x) -> x + 2);
            }
        }
        if(output.get().length() == 2*n){
            System.out.println(output.get());
        }
    }
}
