package ZuoChengYun.dynamicPro;

/**
 * 斐波那契
 */
public class Fibonacci {

    /**
     * 暴力递归.O(2^n)
     * @param n 第n个斐波那契数
     * @return
     */
    public int solve1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return solve1(n-1) + solve1(n-2);
    }

    /**
     * 动态规划：对暴力递归的优化，采用空间换时间
     * O(n)
     * @param n
     * @return
     */
    public int solve2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int t1 = 1;
        int t2 = 1;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = t1 + t2;
            t1 = t2;
            t2 = res;
        }
        return res;
    }

    //还可以利用矩阵来将时间复杂度降为O(logn)；代码在Other包下的Fibonacci

    public static void main(String[] args) {
        Fibonacci instance = new Fibonacci();
        if (instance.solve1(10) == instance.solve2(10)) {
            System.out.println(instance.solve2(10));
        }else {
            System.out.println("failure");
        }
    }
}
