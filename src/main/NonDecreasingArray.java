public class NonDecreasingArray {
    public boolean checkPossibility(int[] nums) {
        int countFalse = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] > nums[i + 1]) {
                countFalse++;
            }
        }
        return countFalse <= 1;
    }
}
