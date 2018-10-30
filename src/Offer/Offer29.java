package Offer;

public class Offer29 {

    public void solve(int[][] matrix) {
        if (matrix == null) return;
        int row = matrix.length; // 行数
        int col = matrix[0].length; // 列数
        int startRowBound = (row+1)/2; // 每次循环时外层开始坐标的行位置
        int startColBound = (col+1)/2; // 每次循环时外层开始坐标的列位置
        for (int i = 0, j = 0; i < startRowBound && j < startColBound; i++, j++) {
            print(matrix, i, j, row, col);
            row = row-2;
            col = col-2;
        }
    }

    /**
     * 打印外层一圈
     * @param matrix 二维数组
     * @param i 开始位置的行坐标
     * @param j 开始位置的列坐标
     * @param row 外层的行数
     * @param col 外层的列数
     */
    private void print(int[][] matrix, int i, int j, int row, int col) {
        int edgeRow = i+row-1; // 行边界
        int edgeCol = j+col-1; // 列边界
        for (int k = j; k <= edgeCol; k++) {
            System.out.print(matrix[i][k] + ", ");
        }
        for (int k = i+1; k <= edgeRow; k++) {
            System.out.print(matrix[k][edgeCol] + ", ");
        }

        if (i != edgeRow) { // 防止只有一行时重复打印
            for (int k = edgeCol-1; k >= j; k--) {
                System.out.print(matrix[edgeRow][k] + ", ");
            }
        }

        if (j != edgeCol) { // 防止只有一列时重复打印
            for (int k = edgeRow-1; k > i; k--) {
                System.out.print(matrix[k][j] + ", ");
            }
        }
    }

    public static void main(String[] args) {
        Offer29 instance = new Offer29();

        int[][] matrix = {{1,2,3,4}, {10,11,12,5}, {9,8,7,6}};
        instance.solve(matrix);

        System.out.println();
        int[][] matrix2 = {{1,2,3},{8,9,4},{7,6,5}};
        instance.solve(matrix2);

        System.out.println();
        int[][] matrix3 = {{1,2,3,4,5},{18,19,20,21,6},{17,28,29,22,7},{16,27,30,23,8},{15,26,25,24,9},{14,13,12,11,10}};
        instance.solve(matrix3);

        System.out.println();
        int[][] matrix4 = {{1},{2},{3},{4}};
        instance.solve(matrix4);

        System.out.println();
        int[][] matrix5 = {{1,2,3,4,5}};
        instance.solve(matrix5);
    }
}
