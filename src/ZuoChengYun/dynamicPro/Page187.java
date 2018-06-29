package ZuoChengYun.dynamicPro;

/**
 * 矩阵最短路径和
 */
public class Page187 {

    //采用辅助矩阵dp[][]， 时间与空间复杂度都为O(M*N)
    public int solve(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] dp = new int[row][column];

        dp[0][0] = matrix[0][0];
        for (int i = 1; i < column; i++) {
            dp[0][i] = matrix[0][i] + dp[0][i-1];
        }

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + matrix[i][0];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + matrix[i][j];
            }
        }
        return dp[row-1][column-1];
    }

    //对方法进行改进，将空间复杂度压为O(min{M,N})
    public int solveAdvan(int[][] matrix) {
        int more = Math.max(matrix.length, matrix[0].length); //代表长的一边
        int less = Math.min(matrix.length, matrix[0].length); //代表短的一边

        int[] dp = new int[less];
        boolean rowShorter = less == matrix.length;//是否行比列短

        dp[0] = matrix[0][0];
        for (int i = 1; i < less; i++) {
            dp[i] = dp[i-1] + (rowShorter ? matrix[i][0] : matrix[0][i]);
        }

        for (int i = 1; i < more; i++) {
            dp[0] += (rowShorter ? matrix[0][i] : matrix[i][i]);
            for (int j = 1; j < less; j++) {
                dp[j] = Math.min(dp[j-1], dp[j]) + matrix[i][j];
            }
        }
        return dp[less-1];
    }

    public static void main(String[] args) {
        Page187 instance = new Page187();
        int[][] matrix = {{1,3,5,9}, {8,1,3,4}, {5,0,6,1}, {8,8,4,0}};
        System.out.println(instance.solve(matrix));//12

        System.out.println(instance.solveAdvan(matrix));
    }
}
