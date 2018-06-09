package ZuoChengYun;

/**
 * "之"字形打印矩阵
 * 两个指针：(sr,sc)先从第一行往右移动，到头就往下；(er,ec)第一列往下，到头往右
 * print方法里boolean控制打印方向，为true代表从下往上打印
 * 注意 列比行长 与 行比列长 拐弯后不同的打印方向
 */
public class Page335 {

    public void solve(int[][] matrix) {
        int rowL = matrix.length;
        int columnL = matrix[0].length;
        int sr = 0, sc = 0, er = 0, ec = 0;

//        //只有一列
//        if (columnL == 1) {
//            while (sr < rowL) {
//                System.out.print(matrix[sr][0] + " ");
//                sr++;
//            }
//            return;
//        }
//        //只有一行
//        if (rowL == 1) {
//            while (sc < columnL) {
//                System.out.print(matrix[0][sc] + " ");
//                sc++;
//            }
//            return;
//        }

        while (sc < columnL && er < rowL) {
            print(matrix, 0, sc, er, 0, (sc & 1) == 0);
            sc++;
            er++;
        }

        if (sc == columnL && er < rowL) {
            while (er < rowL) {
                print(matrix, ++sr, columnL-1, er, 0, (er & 1) == 0);
                er++;
            }
            //er == rowL
            while (sr < rowL-1) {
                print(matrix, ++sr, columnL-1, rowL-1, ++ec, (sr & 1) == 0);
            }
        } else if (er == rowL && sc < columnL) {
            while (sc < columnL) {
                print(matrix, 0, sc, rowL-1, ++ec, (sc & 1) == 0);
                sc++;
            }
            //sc == columnL
            while (ec < columnL-1) {
                print(matrix, ++sr, columnL-1, rowL-1, ++ec, (sr & 1) == 1);
            }
        } else {
            while (sr < rowL-1) {
                print(matrix, ++sr, columnL-1, rowL-1, ++ec, (sr & 1) == 0);
            }
        }
    }

    //orientation = true代表从下向上打印
    private void print(int[][] matrix, int sr, int sc, int er, int ec, boolean orientation) {
        if (orientation) {
            while (er >= sr) {
                System.out.print(matrix[er][ec] + " ");
                er--;
                ec++;
            }
        } else {
            while (sr <= er) {
                System.out.print(matrix[sr][sc] + " ");
                sr++;
                sc--;
            }
        }
    }

    public static void main(String[] args) {
        Page335 instance = new Page335();

        //列长
        int[][] matrix = {{1,2,3,4}, {10,11,12,5}, {9,8,7,6}};
        instance.solve(matrix);

        //行长
        System.out.println();
        int[][] matrix3 = {{1,2,3,4,5},{18,19,20,21,6},{17,28,29,22,7},{16,27,30,23,8},{15,26,25,24,9},{14,13,12,11,10}};
        instance.solve(matrix3);
        System.out.println();
        System.out.print("1 2 18 17 19 3 4 20 28 16 15 27 29 21 5 6 22 30 26 14 13 25 23 7 8 24 12 11 9 10" + "正确顺序");

        //方阵
        System.out.println();
        int[][] matrix2 = {{1,2,3},{8,9,4},{7,6,5}};
        instance.solve(matrix2);

        //单列
        System.out.println();
        int[][] matrix4 = {{1},{2},{3},{4}};
        instance.solve(matrix4);

        //单行
        System.out.println();
        int[][] matrix5 = {{1,2,3,4,5}};
        instance.solve(matrix5);

        System.out.println();
        int[][] matrix6 = {{1,2,3,4,5},{6,7,8,9,10}};
        instance.solve(matrix6);

        System.out.println();
        int[][] matrix7 = {{1,2},{3,4},{5,6},{7,8}};
        instance.solve(matrix7);
    }
}
