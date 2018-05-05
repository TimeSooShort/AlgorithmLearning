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

    //DFS
    public int minPathSum2(int[][] grid) {
        return dfs(grid, new int[grid.length][grid[0].length], grid.length-1, grid[0].length-1);
    }
    private int dfs(int[][] grid, int[][] memo, int x, int y){
        if(x < 0 || y < 0) return Integer.MAX_VALUE;
        if(memo[x][y] > 0) return memo[x][y];
        if(x == 0 && y == 0) return grid[x][y];
        memo[x][y] = grid[x][y] + Math.min(dfs(grid, memo, x-1, y), dfs(grid, memo, x, y-1));
        return memo[x][y];
    }
}
