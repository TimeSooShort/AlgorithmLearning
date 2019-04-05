package LeetCode.binarySearch;

public class LeetCode153 {
    public int findMin(int[] nums) {
        int l = 0, h = nums.length-1;
        while(l < h) {
            int mid = l+(h-l)/2;
            if(nums[mid] < nums[h]) h = mid;
            else l = mid+1;
        }
        return nums[h];
    }
}
