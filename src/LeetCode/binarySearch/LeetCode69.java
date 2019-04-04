package LeetCode.binarySearch;

public class LeetCode69 {
    public static int mySqrt(int x) {
        if(x <= 1) return x;
        int l = 1, h = x;
        while(l <= h) { // l <= h
            int mid = l+(h-l)/2;
            int sqrt = x/mid; // 注意越界问题
            if(sqrt == mid) return mid;
            else if (sqrt > mid) l = mid+1;
            else h = mid - 1;
        }
        return h;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
}
