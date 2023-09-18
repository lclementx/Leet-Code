import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> numsList = IntStream.of(nums).boxed().collect(Collectors.toList());
        Set<List<Integer>> coll = new HashSet<>();
        coll = subList(numsList,coll);
        List<List<Integer>> res = new ArrayList<>();
        for(List<Integer> triplets: coll){
            int sum = triplets.stream().reduce(Math::addExact).get();
            if(sum == 3){

            }
        }
        return (List<List<Integer>>) subList(numsList,coll);
    }

    public Set<List<Integer>> subList(List<Integer> list, Set<List<Integer>> permutations){
        if(list.size() == 1){
            return Set.of(list);
        }
        for(Integer i: list){
            List<Integer> listCopy = new ArrayList<>(list);
            listCopy.remove(i);
            Set<List<Integer>> smallerSets = subList(listCopy,permutations);
            Set<List<Integer>> newSets = new HashSet<>();
            for(List<Integer> sets: smallerSets){
                if (sets.size() < 3) {
                    sets.add(i);
                    newSets.add(sets);
                }
            }
            permutations.addAll(newSets);
        }
        return permutations;
    }
}
