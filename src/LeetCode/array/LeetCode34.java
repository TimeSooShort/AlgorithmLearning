package LeetCode.array;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].
 */
public class LeetCode34 {

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) return result;

        int divide = binary(nums, 0, nums.length-1, target);
        if(divide < 0) return result;
        result[0] = divide;;
        if(divide > 0) {
            int start = lastLeft(nums, divide, target);
            if (start >= 0) result[0] = start;
        }

        if(divide == nums.length-1) result[1] = divide;
        else {
            int end = lastRight(nums, divide, target);
            if(end < 0) result[1] = divide;
            else result[1] = end;
        }
        return result;
    }
    private int binary(int[] nums, int lo, int hi, int target){
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid] < target) lo = mid + 1;
            else if(nums[mid] > target) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
    private int lastLeft(int[] nums, int index, int target){
        int preIndex = -1;
        while(index > 0){
            preIndex = index;
            index = binary(nums, 0, index-1, target);
            if(index == 0){
                return index;
            }
        }
        return preIndex;
    }
    private int lastRight(int[] nums, int index, int target){
        int preIndex = -1;
        index = binary(nums, index+1, nums.length-1, target);
        while(index > 0){
            preIndex = index;
            index = binary(nums, index+1, nums.length-1, target);
            if(index == nums.length-1){
                return index;
            }
        }
        return preIndex;
    }
}
