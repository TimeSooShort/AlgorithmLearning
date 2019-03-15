package LeetCode.array;

/**
 * 将数组 [1,17,5,10,13,15,10,5,16,8] 转换为 [up, down, up, up, up, down, down, up, down]；
 * 转换依据是 当nums[ i ] - nums[ i - 1 ] > 0, 则记位up，否则为down；
 * 则以nums[ i ] 为结尾的摆动序列的最大长度为 前一个down的大小加一。
 */
public class LeetCode376 {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int up = 1, down = 1;
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) up = down + 1;
            else if (nums[i] < nums[i-1]) down = up + 1;
        }
        return Math.max(up, down);
    }
}
