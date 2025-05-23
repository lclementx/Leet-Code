import java.util.*;

public class EclipseCodility {
    public int questionOne(int[] A) {
//        int N = A.length;
//        int result = 0;
//        for (int i = 0; i < N; i++)
//            for (int j = 0; j < N; j++)
//                if (A[i] == A[j])
//                    result = Math.max(result, Math.abs(i - j));
//        return result;

        Map<Integer, Map<String,Integer>> placingMap =  new HashMap<>();
        int N = A.length;
        int result = 0;
        for (int i = 0; i < N; i++) {
            int value = A[i];
            Map<String,Integer> numberPlacing = placingMap.getOrDefault(value, new HashMap<>());
            if(!numberPlacing.containsKey("firstSeen")) {
                numberPlacing.put("firstSeen",i);
                placingMap.put(value,numberPlacing);
            }
            else {
                numberPlacing.put("nextSeen",i);
                placingMap.put(value,numberPlacing);
                Integer firstSeen =  numberPlacing.get("firstSeen");
                Integer nextSeen =  numberPlacing.get("nextSeen");
                result = Math.max(result,nextSeen - firstSeen);
            }
        }
        return result;
    }

    public boolean questionTwo(int[] A, int[] B) {
        Map<Integer, Set<Integer>> fromVertexToVertices = new HashMap<>();
        Set<Integer> allVertex = new HashSet<>();
        for(int i = 0; i < A.length; i++) {
            int fromVertex = A[i];
            Set<Integer> destination = fromVertexToVertices.getOrDefault(fromVertex,new HashSet<>());
            destination.add(B[i]);
            fromVertexToVertices.put(fromVertex,destination);
            allVertex.add(A[i]);
            allVertex.add(B[i]);
        }
        Set<Integer> seenVertex =  new HashSet<>();
        //take first vertex, start searching to see if we can go back to itself. If it can't, it cannot be cyclical.
        int startingVertex = A[0];
        boolean cyclical = terminalMatchFirst(startingVertex, startingVertex, fromVertexToVertices,seenVertex);
        //if its cyclical, check if we have gone through all vertex, we assume every vertex has to be connected to something else somehow (even itself) in order
        //for it to be in the input. otherwise its invalid input
        boolean hasSeenAllVertex = seenVertex.containsAll(allVertex);
        return cyclical && hasSeenAllVertex;
    }

    private boolean terminalMatchFirst(int startingVertex, int currentVertex, Map<Integer,Set<Integer>> vertexMap, Set<Integer> seenVertex) {
        System.out.println("Current Node: " + currentVertex);
        Set<Integer> nextVertices = vertexMap.getOrDefault(currentVertex,new HashSet<>());
        seenVertex.add(currentVertex);
        boolean match = false;
        if(nextVertices.isEmpty()) {
            return currentVertex == startingVertex;
        }
        for(Integer nextVertex : nextVertices) {
            //TODO: SHOULD JUST CHECK WEHTHER NEXT VERTEX IS IN SEEN VERTEX, WILL HANDLE ANY CYLCES.
            System.out.println("Next Node: " + nextVertex);
            if(startingVertex == nextVertex) {
                return true;
            }
            if(nextVertex == currentVertex) {
                return false;
            }
            boolean result = terminalMatchFirst(startingVertex, nextVertex, vertexMap, seenVertex);
            match = match || result;
        }
        return match;
    };
}
