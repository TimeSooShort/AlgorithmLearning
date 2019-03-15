package LeetCode.array;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode646 {

    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int res = 0;
        int last = Integer.MIN_VALUE;
        for(int[] arr : pairs) {
            if (arr[0] > last) {
                res++;
                last = arr[1];
            }
        }
        return res;
    }
}
