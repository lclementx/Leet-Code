import java.util.HashMap;
import java.util.TreeMap;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        TreeMap<Integer,Integer> sortedNums = new TreeMap<>();
        //I don't care about negative numbers
        for(int n : nums){
            if(n > 0){
                Integer count = sortedNums.getOrDefault(n,0);
                sortedNums.put(n,count + 1);
            }
        }

        if(sortedNums.isEmpty()){
            return 1;
        }

        int retval = 0;
        Integer smallestElem = sortedNums.firstKey();
        if(smallestElem > 1){
            return 1;
        }else{
            for(int i = smallestElem; i < Integer.MAX_VALUE; i++){
                if(!sortedNums.containsKey(i)){
                    retval = i;
                    break;
                }
            }
        }

        return retval;
    }

    public int firstMissingPositiveMethod2(int[] nums) {
        HashMap<Integer,Boolean> numMap = new HashMap<>();
        //I don't care about negative numbers
        for(int n : nums){
            if(n > 0){
                numMap.put(n,true);
            }
        }

        int retval = 0;

        for(int i = 0; i < Integer.MAX_VALUE; i++){
            if(!numMap.containsKey(i)){
                retval = i;
                break;
            }
        }

        return retval;
    }
}