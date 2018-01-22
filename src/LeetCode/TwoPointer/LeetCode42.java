package LeetCode.TwoPointer;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode42 {
    private int i = 1;
    public int trap(int[] height) {
        int area = 0;
        //排除数组前面递增序列
        for(; i < height.length; i++) {
            if (height[i] >= height[i-1]) {
                continue;
            } else {
                break;
            }
        }
        area = compares(height, i-1, 0);
        while (i != height.length-1){
            area = compares(height, ++i, area);
        }
        return area;
    }

    private int compares(int[] ht, int start, int area){
        int left = start, right = start+1;
        while (right < ht.length) {
            if (ht[left] <= ht[right]){
                area += (right-left-1) * ht[left];
                for(int i = left+1; i < right; i++){
                    area = area-ht[i];
                }
                left = right;
            }
            right++;
        }
        i = left;
        return area;
    }
    //...............................................................
    //解法2：利用减法，双指针
    public int trap2(int[] height) {
        if (height.length <= 2) return 0;
        int area = 0, left = 0, right = height.length-1;
        int leftMaxHeight = height[left], rightMaxHeight = height[right];
        while (left < right) {
            if (height[left] < height[right]) {
                leftMaxHeight = Math.max(leftMaxHeight, height[++left]);
                area += leftMaxHeight - height[left];
            } else {
                rightMaxHeight = Math.max(rightMaxHeight, height[--right]);
                area += rightMaxHeight - height[right];
            }
        }
        return area;
    }

    public static void main(String[] args) {
        int[] array = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] array1 = {5,4,3,2,3};
        LeetCode42 code = new LeetCode42();
        int result = code.trap(array1);
        System.out.println(result);
    }
}
