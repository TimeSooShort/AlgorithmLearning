package ZuoChengYun;

/**
 * N皇后问题
 */
public class Page238 {

    public int solve(int n) {
        assert n > 0;
        int[] arr = new int[n];
        return process(arr, 0, n);
    }

    /**
     * arr 下标代表行，存储元素代表列，arr便代表N*N的棋盘。
     * 问题分两部分来看：当前行的列选择及之后部分的选择。
     * @param arr 代表N*N的棋盘
     * @param i 代表当前行
     * @param n 数组大小
     * @return 返回结果
     */
    private int process(int[] arr, int i, int n) {
        if (i == n) return 1;
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(arr, i, j)) {
                arr[i] = j;
                res += process(arr, i+1, n);
            }
        }
        return res;
    }

    /**
     * 判断在ar[i]处放j是否符合规则：不与前面行所放元素在同一列，且不在同一斜线
     * @param arr 下标代表行，存储元素代表列
     * @param i 第i行
     * @param j 第j列
     * @return 返回true代表在i行放在j列可以
     */
    private boolean isValid(int[] arr, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (arr[k] == j || Math.abs(k-i) == Math.abs(arr[k]-j))
                return false;
        }
        return true;
    }

    // 对算法进行优化

    public int solve2(int n) {
        assert n > 0 && n <= 32;
        int upperLim = n == 32 ? -1 : (1 << n)-1;
        return process(upperLim, 0, 0, 0);
    }

    /**
     * 变量pos表示在colLim, leftDiaLim, rightDiaLim 这三个状态的影响下还有哪些位置是可以选择的，
     * 1代表可以选择。mostRightOne表示最右边的1在什么位置。
     * @param upperLim 该值表示棋盘已放满情况， 递归过程值不变
     * @param colLim 表示递归到上一行为止，在哪些列上已经放置了皇后，1表示放置
     * @param leftDiaLim 表示递归到上一行为止，因为受已经放置的所有皇后的左下方斜线影响，
     *                   导致当前行不能放置皇后。1表示不能放置
     * @param rightDiaLim 表示递归到上一行为止，因为受已经放置的所有皇后的右下方斜线影响，
     *                   导致当前行不能放置皇后。1表示不能放置
     * @return 返回结果
     */
    private int process(int upperLim, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == upperLim) return 1;
        int pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process(upperLim, colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }
}
