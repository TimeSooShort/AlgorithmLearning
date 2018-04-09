package LeetCode.array;

/**
 * 给一个数组，求该数组所组成的数字的全排列的下一个数字
 * 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 */
public class LeetCode31 {

    public void nextPermutation(int[] nums) {
        int pos = nums.length-1;
        for(; pos > 0; pos--){
            if (nums[pos-1] < nums[pos]) break;
        }
        for(int i=pos, j=nums.length-1; i < j; i++,j--){
            swap(nums, i, j);
        }
        if (pos == 0) return;
        for(int i = pos; i < nums.length; i++){
            if (nums[i] > nums[pos-1]){
                swap(nums, i, pos-1);
                break;
            }
        }
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
