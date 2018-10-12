package Offer;

/**
 * 二维数组中的查找
 */
public class Offer4 {

    public boolean find(int[][] matrix, int target) {

        int row = 0, column = matrix[0].length-1;
        while (row < matrix.length && column >= 0) {
            int item = matrix[row][column];
            if (item > target) column--;
            else if (item < target) row++;
            else return true;
        }
        return false;
    }
}
