package Offer;

public class Offer12 {

    public boolean solve(char[][] matrix, String target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 ||
                target == null || target.length() == 0) {
            return false;
        }
        int rows = matrix.length, cols = matrix[0].length;
        char[] tArr = target.toCharArray();
        boolean[][] visited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPath(matrix, rows, cols, row, col, tArr, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPath(char[][] matrix,int rows, int cols, int row, int col, char[] target,
                            int pathLength, boolean[][] visited) {
        if (pathLength == target.length) return true;
        boolean hasPath = false;
        if (row >= 0 && row < rows && col >= 0 && col < cols &&
                target[pathLength] == matrix[row][col] && !visited[row][col]) {
            ++pathLength;
            visited[row][col] = true;
            hasPath = hasPath(matrix, rows, cols, row, col+1, target, pathLength, visited)
                    || hasPath(matrix, rows, cols, row+1, col, target, pathLength, visited)
                    || hasPath(matrix, rows, cols, row, col-1, target, pathLength, visited)
                    || hasPath(matrix, rows, cols, row-1, col, target, pathLength, visited);
            if (!hasPath) {
                --pathLength;
                visited[row][col] = false;
            }
        }
        return hasPath;
    }
}
