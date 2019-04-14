package LeetCode.bit;

public class LeetCode338 {

    // 方法一
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for(int i = 1; i <= num; i++) {
            res[i] = getOneDigit(i);
        }
        return res;
    }

    private int getOneDigit(int num) {
        int count = 0;
        while(num != 0) {
            count += (num & 1);
            num = (num >>> 1);
        }
        return count;
    }

    // 方法二
    public int[] countBits2(int num) {
        int[] res = new int[num+1];
        fill(res, num, 1,1);
        return res;
    }

    private void fill(int[] res, int num, int cur, int count) {
        if (cur > num || res[cur] != 0) return;
        res[cur] = count;
        // 这两处递归能够保证遍历完数组
        fill(res, num, (cur << 1)+1, count+1);// x * 2^n + 1的二进制1的个数比 x 多1
        fill(res, num, cur << 1, count); //数 x 与 x * 2^n 的二进制1的个数相同
    }
}
