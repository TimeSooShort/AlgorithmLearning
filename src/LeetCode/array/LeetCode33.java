package LeetCode.array;

public class LeetCode33 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if(n == 0) return -1;

        int minIndex = findMinIndex(nums, 0, n-1);

        if (nums[n-1] == target) return n-1;
        int start = nums[n-1] > target ? minIndex : 0;
        int end = nums[n-1] > target ? n-1 : minIndex-1;

        while(start <= end){
            int mid = start+(end-start)/2;
            if(nums[mid] > target) end = mid-1;
            else if(nums[mid] < target) start = mid+1;
            else return mid;
        }
        return -1;
    }
    private int findMinIndex(int[] nums, int lo, int hi){
        int pre = lo, last = hi;
        while(pre < last){
            int mid = pre + (last-pre)/2;
            if(nums[mid] > nums[last]) pre = mid+1;//这里逻辑容易陷入死循环
            else last = mid;
        }
        return pre;
    }
}
