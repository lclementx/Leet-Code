import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MedianTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> nums1List = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> nums2List = Arrays.stream(nums2).boxed().collect(Collectors.toList());
        double median;

        nums1List.addAll(nums2List);
        List<Integer> allSortedNums = nums1List.stream().sorted().collect(Collectors.toList());

        if(allSortedNums.size() % 2 == 0){
            int mid1 = (allSortedNums.size() / 2) - 1;
            int mid2 = (allSortedNums.size() / 2);
            median = (allSortedNums.get(mid1) + allSortedNums.get(mid2))/2.0;
        }else{
            int mid = (allSortedNums.size() + 1 / 2);
            median = allSortedNums.get(mid);
        }

        return median;
    }

    public double findMedianSortedArraysMethod2(int[] nums1, int[] nums2) {
        int pointer1 = 0;
        int pointer2 = 0;
        boolean isEven = false;
        int mid;
        Double median = null;

        //Find Mid
        if((nums1.length + nums2.length) % 2 == 0){
            isEven = true;
            mid = ((nums1.length + nums2.length) / 2);
        }else{
            mid = ((nums1.length + nums2.length  + 1) / 2);
        }

        //Deal with Empty Case
        if(nums1.length == 0){
            if(isEven){
                median = (nums2[mid] + nums2[mid-1])/2.0;
            }else{
                median = (double) nums2[mid];
            }
        } else if (nums2.length == 0) {
            if(isEven){
                median = (nums1[mid] + nums1[mid-1])/2.0;
            }else{
                median = (double) nums1[mid];
            }
        }

        int value1 = nums1[pointer1];
        int value2 = nums2[pointer2];

        int counter = 2;
        //Regular Case
        while(median == null){
            boolean pointer1Moved = false;
            boolean pointer2Moved = false;
            if(counter >= mid){
                if(value1 > value2){
                    if(isEven){
                        median = (nums1[pointer1] + nums1[pointer1])/2.0;
                    }else{
                        median = (double) nums1[pointer1];
                    }
                }else if(value2 > value1){
                    if(isEven){
                        median = (nums2[pointer2] + nums1[pointer1])/2.0;
                    }else{
                        median = (double) nums2[pointer2];
                    }
                }else if(value1 == value2){
                    if(isEven){
                        median = (nums1[pointer1] + nums2[pointer2])/2.0;
                    }else{
                        median = (double) nums2[pointer2];
                    }
                }
            }
            if(value1 > value2){
                if(pointer2 < nums2.length - 1){
                    counter++;
                    pointer2++;
                    value2 = nums2[pointer2];
                    pointer2Moved = true;
                }else{
                    counter++;
                    pointer1++;
                    value1 = nums1[pointer1];
                    pointer1Moved = true;
                }
            }else if(value1 < value2){
                if(pointer1 < nums1.length - 1){
                    counter++;
                    pointer1++;
                    value1 = nums1[pointer1];
                    pointer1Moved = true;
                }else{
                    counter++;
                    pointer2++;
                    value2 = nums2[pointer2];
                    pointer2Moved = true;
                }
            }else if(value1 == value2) {
                if (pointer1 < nums1.length - 1 & pointer2 < nums2.length - 1) {
                    int next_value1 = nums1[pointer1 + 1];
                    int next_value2 = nums2[pointer2 + 1];
                    if(next_value1 > next_value2){
                        counter++;
                        pointer2++;
                        value2 = nums2[pointer2];
                        pointer2Moved = true;
                    }else if(next_value1 > next_value1){
                        counter++;
                        pointer1++;
                        value1 = nums1[pointer1];
                        pointer1Moved = true;
                    }else{
                        counter+=2;
                        pointer1++;
                        pointer2++;
                        value1 = nums1[pointer1];
                        value2 = nums2[pointer2];
                        pointer1Moved = true;
                        pointer2Moved = true;
                    }
                }
            }
        }

        return median;
    }
}
