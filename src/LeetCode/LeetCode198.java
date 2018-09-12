package LeetCode;

public class LeetCode198 {
    public int rob(int[] nums) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) a = Math.max(b, a + nums[i]);
            else b = Math.max(a, b + nums[i]);
        }
        return Math.max(a, b);
    }

    public static void main(String[] args) {
        int[] arr = {9,0,0,9};
        LeetCode198 instance = new LeetCode198();
        System.out.println(instance.rob(arr));
    }
}
