package Offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offer60 {

    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        assert n > 0;
        long[][] matrix = new long[2][6*n+1]; // 注意是long
        for (int i = 1; i <= 6; i++) {
            matrix[0][i] = 1;
        }
        for(int i = 2; i <= n; i++) {
            int base = (i & 1) == 1 ? 1 : 0;
            int sum = (i & 1) == 1 ? 0 : 1;
            for(int j = 1; j <= i*6; j++) {
                matrix[sum][j] = 0;
                for(int k = j-1; k >= 0 && k >= j-6; k--) {
                    matrix[sum][j] += matrix[base][k];
                }
            }
        }
        int resultAt = (n & 1) == 1 ? 0 : 1;
        Map<Integer, Double> map = new HashMap<>();
        double total = Math.pow(6,n);
        for(int i = n; i <= 6*n; i++) {
            map.put(i, matrix[resultAt][i]/total);
        }
        return new ArrayList<>(map.entrySet());
    }
}
