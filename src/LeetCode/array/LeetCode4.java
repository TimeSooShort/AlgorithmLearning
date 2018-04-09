package LeetCode.array;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.

 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */
public class LeetCode4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] nums = new int[n1+n2];
        int i = 0, j = 0, k=0;
        while(k < n1+n2){
            if(i >= n1) {
                while(j < n2){
                    nums[k++] = nums2[j++];
                }
            } else if(j >= n2){
                while(i < n1){
                    nums[k++] = nums1[i++];
                }
            } else if(nums1[i] < nums2[j]){
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        if ((nums.length & 1) == 0){
            return (nums[(n1+n2)/2] + nums[(n1+n2)/2-1] + 0.0)/2;
        } else {
            return nums[(n1+n2)/2];
        }
    }
}
