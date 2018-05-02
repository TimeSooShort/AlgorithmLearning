package LeetCode.array;

public class LeetCode64 {

    public int minPathSum(int[][] grid) {
        //DP
        for(int i = 0; i < grid.length; i++){
            if (i != 0) {
                grid[i][0] += grid[i-1][0];
            }
            for(int j = 1; j < grid[0].length; j++){
                if (i == 0) {
                    grid[i][j] += grid[i][j-1];
                } else {
                    grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
