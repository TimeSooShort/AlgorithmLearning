package LeetCode.array;

public class LeetCode55 {

    public boolean canJump(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > max) return false;
            max = Math.max(nums[i]+i, max); //延展性大于i说明就有到底的可能
        }
        return true;
    }

}
