package LeetCode.binarySearch;

public class LeetCode540 {

    public int singleNonDuplicate(int[] nums) {
        if(nums.length == 1) return nums[0];
        int l = 0, h = nums.length-1;
        while(l <= h) {
            int mid = l+(h-l)/2;
            if(mid == 0 || mid == nums.length-1) return nums[mid];
            else if(nums[mid] != nums[mid-1]) {
                if(nums[mid]!=nums[mid+1]) return nums[mid];
                else {
                    if((mid & 1) == 1) {
                        h = mid-1;
                    }else {
                        l = mid+2;
                    }
                }
            }else {
                if((mid & 1) == 1) {
                    l = mid+1;
                }else{
                    h = mid-2;
                }
            }
        }
        return -1;
    }

    // 优化：利用数组的特点：通过主动选择mid，确保其为偶数，这样其左侧的元素个数就是偶数
    public int singleNonDuplicate2(int[] nums) {
        int l = 0, h = nums.length-1;
        while(l < h) {
            int mid = l+(h-l)/2;
            if((mid & 1) == 1) mid--;
            if(nums[mid] == nums[mid+1]) l = mid+2;
            else h = mid;
        }
        return nums[h];
    }
}
