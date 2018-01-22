package LeetCode.math;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode279 {
    public int numSquares(int n) {
        int[] count = new int[n+1];
        count[0] = 0;
        count[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j*j <= i; j++) {
                min  = Math.min(min, count[i-j*j]+1);
            }
            count[i] = min;
        }
        return count[n];
    }
}
