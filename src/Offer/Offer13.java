package Offer;

public class Offer13 {

    public int solve(int[][] matrix, int threshold) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || threshold < 0) {
            return -1;
        }
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        return count(matrix, rows, cols, 0, 0, threshold, visited);
    }

    private int count(int[][] matrix, int rows, int cols, int row, int col, int threshold,
                      boolean[][] visited) {
        int count = 0;
        if (check(rows, cols, row, col, threshold, visited)) {
            visited[row][col] = true;
            count = 1 + count(matrix, rows, cols, row+1, col, threshold, visited)
                    + count(matrix, rows, cols, row, col+1, threshold, visited)
                    + count(matrix, rows, cols, row-1, col, threshold, visited)
                    + count(matrix, rows, cols, row, col-1, threshold, visited);
        }
        return count;
    }

    private boolean check(int rows, int cols, int row, int col, int threshold,
                          boolean[][] visited) {
        if (row >= 0 && row < rows && col >= 0 && col < cols &&
                getDigitSum(row) + getDigitSum(col) <= threshold &&
                !visited[row][col]) {
            return true;
        }
        return false;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while(num > 0) {
            sum += num%10;
            num /= 10;
        }
        return sum;
    }
}
