package LeetCode.array;

/**
 * Rotate Image;先上下对调，再沿折线（\）对调；或左右对调，在沿反折线（/)对调
 */
public class LeetCode48 {

    public void rotate(int[][] matrix) {
        matrix = swap(matrix);
        int n = matrix.length;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    private int[][] swap(int[][] matrix){
        int n = matrix.length;
        for(int i = 0; i < n/2; i++){
            int[] temp = matrix[i];
            matrix[i] = matrix[n-i-1];
            matrix[n-i-1] = temp;
        }
        return matrix;
    }
}
