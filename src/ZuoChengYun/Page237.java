package ZuoChengYun;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中的最长连续序列,返回其长度
 *  {100，2，34，1，4，3}的最长连续序列为{1，2，3，4},返回4
 */
public class Page237 {

    public int solve(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("参数错误");
        }

        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;

        for (int anArr: arr) {
            if (!map.containsKey(anArr)) {
                map.put(anArr, 1);
                if (map.containsKey(anArr-1)) {
                    max = Math.max(max, merge(map, anArr-1, anArr));
                }
                if (map.containsKey(anArr+1)) {
                    max = Math.max(max, merge(map, anArr, anArr+1));
                }
            }
        }
        return max;
    }

    private int merge(Map<Integer, Integer> map, int less, int more) {
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        map.put(left, len);
        map.put(right, len);
        return len;
    }

    public static void main(String[] args) {
        Page237 instance = new Page237();
        int[] arr = {100,2,34,1,4,3};
        System.out.println(instance.solve(arr));
    }
}
