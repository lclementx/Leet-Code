import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountPrimesTest {

    @Test
    void checkPrime() {
        CountPrimes cp = new CountPrimes();
        System.out.println(cp.checkPrime(2));
    }

    @Test
    void countPrime() {
        CountPrimes cp = new CountPrimes();
        System.out.println(cp.countPrimes(2));
    }
}