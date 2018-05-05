package LeetCode.array;

import java.util.Arrays;

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
        result[0] = divide;
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
    //二分
    private int binary(int[] nums, int lo, int hi, int target){
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid] < target) lo = mid + 1;
            else if(nums[mid] > target) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
    //循环找到最左点
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
    //循环找到最右
    private int lastRight(int[] nums, int index, int target){
        int preIndex = -1;
        index = binary(nums, index+1, nums.length-1, target); //index可能为0
        while(index > 0){
            preIndex = index;
            index = binary(nums, index+1, nums.length-1, target);
            if(index == nums.length-1){
                return index;
            }
        }
        return preIndex;
    }

    //====================================================

    //方法二
    public int[] search(int[] nums, int target){
        if(nums == null || nums.length == 0) return new int[]{-1,-1};

        return new int[]{searchLeft(nums, target), searchRight(nums, target)};
    }

    private int searchLeft(int[] nums, int target){
        int start = 0, end = nums.length-1;
        while (start + 1 < end){  //长度大于2，进行查找；循环到start+1=end停止
            int mid = start + (end-start)/2;
            if(nums[mid] >= target){
                end = mid;
            }else {
                start = mid;  //由于循环的条件这里不能写成start = mid+1；
            }
        }
        if(nums[start] == target){ //这里先判断start，因为nums[0]可能等于target, 其他情况nums[start] != target
            return start;
        }else if(nums[end] == target){
            return end;
        }else {
            return -1;
        }
    }

    private int searchRight(int[] nums, int target){
        int start = 0, end = nums.length-1;
        while(start + 1 < end){
            int mid = start+(end-start)/2;
            if(nums[mid] <= target){
                start = mid;
            }else {
                end = mid;
            }
        }
        if(nums[end] == target){ //这里先判断end，因为nums[nums.length-1]可能等于target，其他情况nums[end] != target
            return end;
        }else if(nums[start] == target){
            return start;
        }else {
            return -1;
        }
    }

    public static void main(String[] args) {
        LeetCode34 test = new LeetCode34();
        int[] nums =  {5,7,7,4,6,8,8,10};
        int[] result = test.search(nums, 8);
        System.out.println(Arrays.toString(result));
        result = test.search(nums, 6);
        System.out.println(Arrays.toString(result));
        result = test.search(nums, 10);
        System.out.println(Arrays.toString(result));
    }
}
