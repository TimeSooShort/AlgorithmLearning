package ZuoChengYun.dynamicPro;

/**
 *  龙与地下城游戏问题
 */
public class Page223 {

    // 空间时间复杂度都为O（M * N）
    public int solve(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("参数不能为null");
        }

        int row = matrix.length;
        int col = matrix[0].length;

        // 动态规划题，dp该如何定义？该题求的是骑士从（0，0）开始到右下角为止，过程中血量有增有减但不能<1
        // 那么它初始时血量至少应是多少。那么我的dp[i][j]定义为：骑士要走上（i，j）位置，并且从该位置往后
        // 选择一条最优路径，那么它至少血量该是多少（即在（i, j）位置之前血量）
        // 该定义下从终点即右下角开始，往左上，最终得到dp[0][0]，即骑士开始至少要拥有的血量
        // dp[i][j] = min{
        //                  max{dp[i+1][j]-matrix[i][j], 1},
        //                  max{dp[i][j+1]-matrix[i][j], 1}
        //               }
        int[][] dp = new int[row][col];

        dp[row-1][col-1] = matrix[row-1][col-1] > 0 ? 1 : (1-matrix[row-1][col-1]);
        for (int i = col-2; i >= 0; i--) {
            dp[row-1][i] = Math.max(dp[row-1][i+1]-matrix[row-1][i], 1);
        }

        for (int i = row-2; i >= 0; i--) {
            dp[i][col-1] = Math.max(dp[i+1][col-1]-matrix[i][col-1], 1);
        }

        for (int i = row-2; i >= 0; i--) {
            for (int j = col-2; j >= 0; j--) {
                dp[i][j] = Math.min(
                        Math.max(dp[i+1][j]-matrix[i][j], 1),
                        Math.max(dp[i][j+1]-matrix[i][j], 1));
            }
        }
        return dp[0][0];
    }

    // ----------------------------------------------------------

    // 空间压缩; 空间复杂度为O(min{M, N}）
    public int solve2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("参数不能为null, 不能为空");
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[] dp;

        if (col <= row) {
            dp = new int[col];
            dp[col-1] = matrix[row-1][col-1] > 0 ? 1 : 1-matrix[row-1][col-1];

            for (int i = col-2; i >= 0; i--) {
                dp[i] = Math.max(dp[i+1]-matrix[row-1][i], 1);
            }

            for (int i = row-2; i >= 0; i--) {
                dp[col-1] = Math.max(dp[col-1]-matrix[i][col-1], 1);
                for (int j = col-2; j >= 0; j--) {
                    dp[j] = Math.min(
                            Math.max(dp[j+1]-matrix[i][j], 1),
                            Math.max(dp[j]-matrix[i][j], 1)
                    );
                }
            }
        }else {
            dp = new int[row];
            dp[row-1] = matrix[row-1][col-1] > 0 ? 1 : 1-matrix[row-1][col-1];

            for (int i = row-2; i >= 0; i--) {
                dp[i] = Math.max(dp[i+1]-matrix[i][col-1], 1);
            }

            for (int i = col-2; i >= 0; i--) {
                dp[row-1] = Math.max(dp[row-1]-matrix[row-1][i], 1);
                for (int j = row-2; j >= 0; j--) {
                    dp[j] = Math.min(
                            Math.max(dp[j+1]-matrix[j][i], 1),
                            Math.max(dp[j]-matrix[j][i], 1)
                    );
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        Page223 instance = new Page223();
        int[][] matrix = {{-2,-3,3}, {-5,-10,1}, {0,30,-5}};
        System.out.println(instance.solve(matrix));
        System.out.println(instance.solve2(matrix));

        int[][] matrix2 = {{-2,-3,3,4}, {-5,-10,1,-7}, {0,30,-5,8}};
        System.out.println(instance.solve(matrix2));
        System.out.println(instance.solve2(matrix2));
    }
}
