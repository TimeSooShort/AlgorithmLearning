package ZuoChengYun;

/**
 * 两个有序的长度相等的数组中，找到上中位数。时间复杂度0（logN）
 * 利用二分法
 * 此方法还可以解决：在两个排序数组中找到第k小的数P468
 */
public class Page465 {

    public int solve(int[] arr1, int[] arr2) {

        assert arr1.length == arr2.length;

        int n = arr1.length;
        int start1 = 0, end1 = n-1, mid1 = 0;
        int start2 = 0, end2 = n-1, mid2 = 0;

        while (start1 < end1) {
            mid1 = start1+(end1-start1)/2;
            mid2 = start2+(end2-start2)/2;
            n = end1-start1+1;
            if (arr1[mid1] == arr2[mid2]) {
                return arr1[mid1];
            }else if (arr1[mid1] > arr2[mid2]) {
                if ((n & 1) == 1) {
                    end1 = mid1; //尽管此时arr1[mid1]不可能是上中位数，但仍将其算入，因为要维持两个数组相等
                    start2 = mid2;
                }else {
                    end1 = mid1;
                    start2 = mid2+1;
                }
            }else {
                if ((n & 1) == 1) {
                    end2 = mid2;
                    start1 = mid1;
                }else {
                    end2 = mid2;
                    start1 = mid1+1;
                }
            }
        }
        return Math.min(arr1[start1], arr2[start2]);
    }

    public static void main(String[] args) {
        Page465 instance = new Page465();
        int[] arr1 = {1,2,3,4};
        int[] arr2 = {3,4,5,6};
        System.out.println(instance.solve(arr1, arr2));

        int[] arr3 = {1,2,3};
        int[] arr4 = {4,5,6};
        System.out.println(instance.solve(arr3,arr4));
    }
}
