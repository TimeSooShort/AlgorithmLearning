package codewars.kyu5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ToSmallest {
    public static long[] smallest(long n) {
        BigDecimal decimal = new BigDecimal(n);
        char[] arr = decimal.toString().toCharArray();
        StringBuilder builder = new StringBuilder();
        if (arr[1] == 0) {
            int i = 2;
            for(; i < arr.length; i++) {
                if (arr[i] > arr[1]) break;
            }
            for (int j = 2; j < i; j++) {
                builder.append(arr[j]);
            }
            builder.append(arr[1]);
            for (int j = i; j < arr.length; j++) {
                builder.append(arr[j]);
            }

        }
        char min = arr[0], minI = 0;
        for (char i = 1; i < arr.length; i++) {
            if (arr[i] <= min) {
                min = arr[i];
                minI = i;
            }
        }
        return null;
    }
}
