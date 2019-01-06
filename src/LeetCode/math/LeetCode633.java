package LeetCode.math;

/**
 * 两个数的平方和
 */
public class LeetCode633 {

    /**
     * 双指针来解决
     * @param target
     * @return
     */
    public boolean solve(int target) {
        int i = 0, j = (int) Math.sqrt(target);
        while(i <= j) {
            int sum = i*i + j*j;
            if (sum == target) return true;
            else if (sum > target) j--;
            else i++;
        }
        return false;
    }

    /**
     * 二分查找。时间复杂度为 O（根号下target * log（target））
     * @param target
     * @return
     */
    public boolean solve2(int target) {
        for (int i = 0; i*i <= target; i++) {
            int another = target - i*i;
            if (binary_search(0, another, another)) return true;
        }
        return false;
    }

    private boolean binary_search(int start, int end, int target) {
        if (start > end) return false;
        int mid = start + (end-start)/2;
        int sum = mid * mid;
        if (sum == target) return true;
        else if (sum > target) return binary_search(start, mid-1, target);
        else return binary_search(mid+1, end, target);
    }
}
