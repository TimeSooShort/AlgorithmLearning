package LeetCode.array;

public class LeetCode416 {

    public boolean canPartition(int[] nums) {
         if(nums == null) return false;
         int sum = 0;
         for(int num : nums) sum += num;
         if((sum & 1) == 1) return false;
         sum /= 2;
         // dp[i][j]表示nums[0 - i-1]组成 j 值的可能性
         boolean[][] dp = new boolean[nums.length+1][sum+1];
         dp[0][0] = true;

         for(int i = 1; i < dp.length; i++){
             for(int j = 1; j < dp[0].length; j++){
                 dp[i][j] = dp[i-1][j];
                 if(j >= nums[i-1]){
                     dp[i][j] = dp[i][j] || dp[i-1][j-nums[i-1]];
                 }
             }
         }
         return dp[nums.length][sum];
    }

    // 空间优化
    public boolean canPartition2(int[] nums) {
        if(nums == null) return false;
        int sum = 0;
        for(int num : nums) sum += num;
        if((sum & 1) == 1) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        for(int i = 1; i <= nums.length; i++){
            for(int j = dp.length-1; j >= 1; j--){
                if(j >= nums[i-1]){
                    dp[j] = dp[j] || dp[j-nums[i-1]];
                }
            }
            dp[0] = false;
        }
        return dp[sum];
    }

    // 递归解法
    public boolean canPartition3(int[] nums) {
        if(nums == null) return false;
        int sum = 0;
        for(int num : nums) sum += num;
        if((sum & 1) == 1) return false;
        return recur(nums, sum/2, nums.length-1);
    }

    private boolean recur(int[] nums, int target, int index){
        if(target == 0) return true;
        if(index < 0 || target < 0) return false;
        if(recur(nums, target-nums[index], index-1)) return true;
        int j = index-1;
        while (j >= 0 && nums[j] == nums[index]) j--;
        return recur(nums, target, j);
    }
}
