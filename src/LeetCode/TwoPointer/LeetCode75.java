package LeetCode.TwoPointer;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode75 {
    public void sortColors(int[] nums) {
        // int ln = nums.length;
        // int left = 0, right = ln-1;
        // while (left < right) {
        //     while (left < ln && nums[left] == 0) left++;
        //     while (right >= 0 && nums[right] != 0) right--;
        //     if (left > right) {
        //         break;
        //     }
        //     nums[right] = nums[left];
        //     nums[left] = 0;
        // }
        // right = ln-1;
        // while(left < right) {
        //     while (left < ln && nums[left] == 1) left++;
        //     while (right >= 0 && nums[right] == 2) right--;
        //     if (left > right){
        //         break;
        //     }
        //     nums[right] = 2;
        //     nums[left] = 1;
        //
        int zero = 0, two = nums.length-1; //[0~zero-1] == 0; [two+1~]==2
        for(int i = 0; i <= two; i++){
            while(nums[i] == 2 && i < two) exch(nums, i, two--);
            while(nums[i] == 0 && i > zero) exch(nums, i, zero++);
        }
    }
    private void exch(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
