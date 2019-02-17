package LeetCode.dp;

// 测试用例中会出现 [1,2] 而不是 [1,2,1]，要注意
public class LeetCode213 {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int ln = nums.length;
        if(ln == 1) return nums[0];
        return Math.max(max(nums, 0, ln-2), max(nums, 1, ln-1));
    }

    private int max(int[] nums, int start, int end) {
        int pre1 = 0, pre2 = 0;
        for(int i = start; i <= end; i++) {
            int cur = Math.max(pre1+nums[i], pre2);
            pre1 = pre2;
            pre2 = cur;
        }
        return pre2;
    }
}
