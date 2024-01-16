import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CountPrimes {
    private TreeSet<Integer> primeSet = new TreeSet<>();
    public int countPrimes(int n) {
        int primeCount = primeSet.size();
        int lowerLimit = primeSet.isEmpty() ? 1 : primeSet.last();
//        System.out.println("Lower Limit: " + lowerLimit);
//        System.out.println("Tree Set: " + primeSet);
        for(int i = n - 1; i > lowerLimit; i--){
            if(checkPrime(i)){
                System.out.println(i);
                primeCount += 1;
            }
        }
        return primeCount;
    }

    public boolean checkPrime(int n){
        if(primeSet.contains(n)){
            return true;
        } else{
            int factorCount = 0;
            double searchLimit = Math.sqrt(n);
            for(int i = 2; i <= searchLimit; i++){
                if(n % i == 0){
                    factorCount += 1;
                    break;
                }
            }
            if(factorCount == 0){
                primeSet.add(n);
                return true;
            }else{
                return false;
            }
        }
    }
}
