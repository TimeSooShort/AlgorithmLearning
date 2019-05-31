package Other.bilibili2019;

import java.util.Scanner;

public class ClockwisePrint {

    public static void clockwise(int[][] matrix,int startR,int startC, int rowLn, int columnLn, StringBuilder result) {
        if (rowLn <= 0 || columnLn <= 0) return;
        for (int i = startC; i < startC+columnLn; i++) {
            result.append(matrix[startR][i]).append(",");
        }
        for (int i = startR+1; i < startR+rowLn; i++) {
            result.append(matrix[i][startC + columnLn - 1]).append(",");
        }
        if (rowLn != 1) {
            for (int i = startC+columnLn-2; i >= startC; i--) {
                result.append(matrix[startR + rowLn - 1][i]).append(",");
            }
        }
        if (columnLn != 1) {
            for (int i = startR+rowLn-2; i > startR; i--) {
                result.append(matrix[i][startC]).append(",");
            }
        }
        clockwise(matrix, startR+1, startC+1, rowLn-2, columnLn-2, result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] cr = scanner.nextLine().trim().split(" ");
            int rowLn = Integer.valueOf(cr[0]);
            int coln = Integer.valueOf(cr[1]);
            if (rowLn == -1) return;
            int[][] matrix = new int[rowLn][coln];
            for (int i = 0; i < rowLn; i++) {
                String[] arr = scanner.nextLine().split(" ");
                for (int j = 0; j < coln; j++) {
                    matrix[i][j] = Integer.valueOf(arr[j]);
                }
            }
            StringBuilder result = new StringBuilder();
            clockwise(matrix, 0, 0, rowLn, coln, result);
            System.out.println(result.substring(0, result.length()-1));
        }
    }
}
