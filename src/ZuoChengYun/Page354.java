package ZuoChengYun;

/**
 * 未排序正数数组中累加和为给定值的最长子序列的长度
 */
public class Page354 {

    public int solve(int[] arr, int k) {
        if (arr == null || arr.length == 0) return -1;
        int left = 0, right = 0, len = 0, sum = arr[0];
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right-left+1);
                sum -= arr[left++];
            }else if (sum > k) {
                sum -= arr[left++];
            }else {
                right++;
                if (right == arr.length) break;
                sum += arr[right];
            }
        }
        return len;
    }
}
