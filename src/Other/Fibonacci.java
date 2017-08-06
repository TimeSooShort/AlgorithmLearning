package Other;

public class Fibonacci {

	public int[][] matrixPower(int[][] matrix, int p) {
		int[][] result = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			result[i][i] = 1;
		}
		int[][] temp = matrix;
		for(; p!= 0; p>>>=1) {
			if((p & 1) != 0) {
				result = muliMatrix(result, temp);
			}
			temp = muliMatrix(temp, temp);
		}
		return result;
	}
	
	public int[][] muliMatrix(int[][] matrix1, int[][] matrix2) {
		int[][] result = new int[matrix1.length][matrix2[0].length];
		for(int i = 0; i < matrix1.length; i++) {
			for(int j = 0; j < matrix2[0].length; j++) {
				for(int k = 0; k < matrix2.length; k++) {
					result[i][j] += matrix1[i][k]*matrix2[k][j];
				}
			}
		}
		return result;
	}
	
	public int fibonacci(int n) {
		if(n < 1) {
			return 0;
		}
		if(n == 1 || n ==2) {
			return 1;
		}
		int[][] base = {{1, 1}, {1, 0}};
		int[][] result = matrixPower(base, n-2);
		return result[0][0] + result[1][0];
	}
}
