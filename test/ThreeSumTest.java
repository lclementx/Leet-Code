import org.junit.jupiter.api.Test;

class ThreeSumTest {

    @Test
    void threeSum() {
        int[] input = {1,2,3,3};
        ThreeSum ts = new ThreeSum();
        System.out.println(ts.threeSum(input));
    }
}