package ZuoChengYun;

/**
 * 顺时针转圈打印矩阵
 */
public class Page331 {

    public void solve(int[][] matrix) {
        int line = matrix.length;
        int row = matrix[0].length;

        //其实把for改成while看起来更干净些，下面的print里也是
        //PS：row 英语原意是行 column是列
        int startLine = 0, startRow = 0, endLine = line-1, endRow = row-1;
        for (; startLine <= endLine && startRow <= endRow; startLine++,startRow++,endLine--,endRow--) {
            print(matrix, startLine, startRow, endLine, endRow);
        }
    }

    /**
     *
     * @param matrix 矩阵
     * @param startLine 左上角开始节点的行
     * @param startRow 左上角开始节点的列
     * @param endLine 右下角结束节点的行
     * @param endRow 右下角结束节点的列
     */
    private void print(int[][] matrix, int startLine, int startRow, int endLine, int endRow) {
        int i;
        if (startLine == endLine) {
            for (i = startRow; i <= endRow; i++) {
                System.out.print(matrix[startLine][i] + " ");
            }
            return;
        }
        if (startRow == endRow) {
            for (i = startLine; i <= endLine; i++) {
                System.out.print(matrix[i][startRow] + " ");
            }
            return;
        }
        for (i = startRow; i <= endRow; i++) {
            System.out.print(matrix[startLine][i] + " ");
        }

        for (i = startLine + 1; i <= endLine; i++) {
            System.out.print(matrix[i][endRow] + " ");
        }

        for(i = endRow-1; i >= startRow; i--) {
            System.out.print(matrix[endLine][i] + " ");
        }

        for (i = endLine-1; i > startLine; i--) {
            System.out.print(matrix[i][startRow] + " ");
        }
    }

    public static void main(String[] args) {
        Page331 instance = new Page331();

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
