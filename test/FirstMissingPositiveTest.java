
class FirstMissingPositiveTest {

    @org.junit.jupiter.api.Test
    void firstMissingPositive() {
        int[] input  = {7,8,9,11,12};
        FirstMissingPositive fmp = new FirstMissingPositive();
        System.out.println(fmp.firstMissingPositive(input));
    }

    @org.junit.jupiter.api.Test
    void firstMissingPositive_0() {
        int[] input  = {0};
        FirstMissingPositive fmp = new FirstMissingPositive();
        System.out.println(fmp.firstMissingPositiveMethod2(input));
    }
}