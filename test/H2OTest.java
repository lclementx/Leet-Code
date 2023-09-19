import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class H2OTest {

    @Test
    void createHydrogen()  {
        String[] input = {"H","O","O","H","H","H"};
        H2O h2o = new H2O();
        ExecutorService executor = Executors.newCachedThreadPool();

        for(String atom : input){
            if(atom.equals("O")){
                executor.execute(() -> {
                    try {
                        h2o.oxygen(() -> System.out.print("O"));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }else{
                executor.execute(() -> {
                    try {
                        h2o.hydrogen(() -> System.out.print("H"));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            });
            }
        }

    }
    @Test
    void createHydrogenMore() {
        String[] input = {"O", "O", "H", "H", "O", "H", "H", "H", "H"};
        H2O h2o = new H2O();
        ExecutorService executor = Executors.newCachedThreadPool();

        for (String atom : input) {
            if (atom.equals("O")) {
                executor.execute(() -> {
                    try {
                        h2o.oxygen(() -> System.out.println("O: " + System.currentTimeMillis()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                executor.execute(() -> {
                    try {
                        h2o.hydrogen(() -> System.out.println("H: " + System.currentTimeMillis()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
}