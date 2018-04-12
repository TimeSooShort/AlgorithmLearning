package liuyubobo.LeetCode;

import liuyubobo.SortTestHelper;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 */
public class LeetCode33 {

    //方法一
    public static int search(Integer[] nums, int target) {
        int n = nums.length;
        if(n == 0) return -1;
        if(nums[0] < nums[n-1] || n == 1) return binary(nums, 0, n-1, target);

        int pre = 0, last = n-1;
        while(pre < last){
            if(nums[pre] > nums[last]){
                pre++;
                last--;
                continue;
            }
            break;
        }
        int biggest = 0;
        if (nums[pre-1] > nums[pre]) biggest = pre-1;
        else if (nums[last+1] < nums[last]) biggest = last;

        if(target > nums[n-1]) return binary(nums, 0, biggest, target);
        else if(target < nums[n-1]) return binary(nums, biggest+1, n-1, target);
        else return n-1;
    }
    private static int binary(Integer[] nums, int lo, int hi, int target){

        if(lo >= hi){
            if(target == nums[lo]) return lo;
            return -1;
        }

        int mid = lo+(hi-lo)/2;
        if(target > nums[mid]) return binary(nums, mid+1, hi,target);
        else if(target < nums[mid]) return binary(nums, lo, mid-1, target);
        else return mid;
    }

    //方法二更快
    public static Integer sort(Integer[] arr, int target){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 200000;
        Integer[] array = SortTestHelper.orderedArray(n);
        Integer[] other = new Integer[n];
        System.arraycopy(array, 80000, other, 0, 120000);
        System.arraycopy(array, 0, other, 120000, 80000);
        long startTime = System.currentTimeMillis();
        int result = search(other, 98765);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
        System.out.println(result);

        startTime = System.currentTimeMillis();
        result = sort(other, 98765);
        endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
        System.out.println(result);
    }
}
