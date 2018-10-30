package ZuoChengYun;

import java.util.HashMap;
import java.util.Map;

/**
 * 未排序数组中有正有负有0，求累加和为给定值的最长子数组长度
 * 补充问题一：未排序数组中有正有负有0，求正数与负数个数相等的最长子数组长度？将正数变为1，负数为-1，
 *              问题转化为求给定值为0的最长子数组长度
 * 补充问题二： 未排序数组中只有1和0，求1和0数目相等的最长子数组长度？将0变为-1，问题转化为求
 *              给定值为0的最长子数组长度。
 */
public class Page355 {

    public int solve(int[] arr, int k) {
        if (arr == null || arr.length == 0) return -1;
        // key：从arr[0]开始到当前的累加和；value：该累加和第一次出现的位置i值
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum -k)) {
                len = Math.max(i-map.get(sum-k), len);
            }
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return len;
    }
}
