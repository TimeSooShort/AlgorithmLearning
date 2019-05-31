package Other.bilibili2019;

import java.util.Scanner;

public class MinPath {
    public static int minPath(int[][] matrix) {
        int[] dp = new int[matrix.length];
        dp[0] = matrix[0][0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = matrix[0][i] + dp[i-1];
        }
        for (int i = 1; i < dp.length; i++) {
            dp[0] += matrix[i][0];
            for (int j = 1; j < dp.length; j++) {
                dp[j] = Math.min(dp[j-1], dp[j]) + matrix[i][j];
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
//        int[][] matrix = {{5,5,7},{6,7,8},{2,2,4}};
//        System.out.println(minPath(matrix));
        Scanner scanner = new Scanner(System.in);
        int ln = Integer.valueOf(scanner.nextLine());
        int[][] matrix = new int[ln][ln];
        for (int i = 0; i < ln; i++) {
            String[] arr = scanner.nextLine().trim().split(",");
            for (int j = 0; j < ln; j++) {
                matrix[i][j] = Integer.valueOf(arr[j]);
            }
        }
        System.out.println(minPath(matrix));
    }
}
