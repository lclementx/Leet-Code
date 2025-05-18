import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonDecreasingArrayTest {

    @Test
    void checkPossibility() {
        NonDecreasingArray nda = new NonDecreasingArray();
        int[] input = {4,2,1};
        System.out.println(nda.checkPossibility(input));
    }
}