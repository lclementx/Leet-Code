import org.junit.jupiter.api.Test;

class MedianTwoSortedArraysTest {

    @Test
    void findMedianSortedArrays() {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        MedianTwoSortedArrays mtsa = new MedianTwoSortedArrays();
        mtsa.findMedianSortedArraysMethod2(nums1,nums2);
    }

    @Test
    void findMedianSortedArrays_123() {
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        MedianTwoSortedArrays mtsa = new MedianTwoSortedArrays();
        mtsa.findMedianSortedArraysMethod2(nums1,nums2);
    }
}