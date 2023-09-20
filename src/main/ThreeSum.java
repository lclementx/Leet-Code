import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreeSum {
    private Map<Set,Boolean> cache = new HashMap<>();

    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> numsList = IntStream.of(nums).boxed().collect(Collectors.toList());
//        return combinations(numsList, new ArrayList<>(),3);
        return optimized(nums);
    }

    public List<List<Integer>> nested(List<Integer> input){
        List<List<Integer>> combinations = new ArrayList<>();
        for(int i = 0; i < input.size() ; i++){
            for(int j = i+2; j < input.size() ; j++){
                for(int k = j+2; k < input.size() ; k++){
                    if( i != j & j != k){
                        int sum = input.get(i) + input.get(j) + input.get(k);
                        List<Integer> com = List.of(input.get(i),input.get(j),input.get(k));
                        if(sum == 0){
                            Set triplet = new HashSet(com);
                            if(!cache.containsKey(triplet)){
                                combinations.add(com);
                                cache.put(triplet,Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }
        return combinations;
    }

    public List<List<Integer>> optimized(int[] input){
        Arrays.sort(input);
        Set<List<Integer>> ans = new HashSet<>();
        for(int i=0;i < input.length-2; i++){
            int p1 = i+1;
            int p2 = input.length-1;
            while(p1 < p2){
                int sum = input[i]+input[p1]+input[p2];
                if(sum == 0){
                    List<Integer> sp = new ArrayList<>();
                    sp.add(input[i]);
                    sp.add(input[p1]);
                    sp.add(input[p2]);

                    ans.add(sp);
                    p1++;
                }
                else if(sum < 0){
                    p1++;
                }
                else{
                    p2--;
                }
            }
        }
        return new ArrayList<>(ans);
    }


    public List<List<Integer>> combinations(List<Integer> list, List<Integer> comb, int n){
        if (comb.size() == n) { // base condition of the recursion
            List<List<Integer>> result = new ArrayList<>();
            if(comb.stream().reduce(Integer::sum).get().equals(0)){
                Set triplet = new HashSet<>(comb);
                if(!cache.containsKey(triplet)){
                    result.add(comb);
                    cache.put(triplet,true);
                }
            }
            return result;
        }

        List<List<Integer>> result = new ArrayList<>();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            // taking an element from a source
            Integer item = iterator.next();
            iterator.remove(); // in order not to get repeated the element has to be removed

            // creating a new combination using existing as a base
            List<Integer> newComb = new ArrayList<>(comb);
            newComb.add(item); // adding the element that was removed from the source
            result.addAll(combinations(new ArrayList<>(list), newComb, n)); // adding all the combinations generated
        }
        return result;
    }
}
