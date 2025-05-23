import org.junit.jupiter.api.Test;

public class EclipseCodilityTest {

    @Test
    public void testQuestionOne() {
        EclipseCodility ec = new EclipseCodility();
        int[] A = {4,6,2,2,6,6,1};
        int result = ec.questionOne(A);
        System.out.println(result);
    }

    @Test
    public void testQuestionTwo() {
        EclipseCodility ec = new EclipseCodility();
        int[] A = {1,2,2,3,3};
        int[] B = {2,3,3,4,5};
        System.out.println(ec.questionTwo(A,B));
    }

}
