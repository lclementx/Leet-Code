import java.util.*;


public class GetRandomO1 {

    private final Map<Integer,Integer> internalMap = new HashMap<>();

    private List<Integer> internalList = new ArrayList<>();

    private final Random random = new Random();

    public GetRandomO1() {
    }

    public boolean insert(int val) {
        if( internalMap.containsKey(val) ) {
            return false;
        }
        internalList.add(val);
        internalMap.put(val,internalList.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if(!internalMap.containsKey(val)) {
            return false;
        }
        int index = internalMap.get(val);
        internalList.set(index, internalList.get(internalList.size() - 1)); //set the index as the last element
        internalMap.put(internalList.get(index), index); //put the last element in place of the element being removed
        //remove the last element from the arraylist - this prevents shifting from changing all other element's index
        internalList.remove(internalList.size() - 1);
        internalMap.remove(val);
        return true;
    }

    public int getRandom() {
        int index = this.random.nextInt(internalList.size());
        return internalList.get(index);
    }
}
