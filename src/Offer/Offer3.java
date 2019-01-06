package Offer;

/**
 * 数组中重复的数字。解决方法：1.排序O(nlogn)。2，利用hash，时间，空间复杂度为O(n)
 * 下面的算法的时间复杂度为O(n)，空间为O(1)
 */
public class Offer3 {

    public static int solve(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] == i) {
                i++;
                continue;
            }
            if (arr[arr[i]] == arr[i]) {
                return arr[i];
            }
            int temp = arr[i];
            arr[i] = arr[temp];
            arr[temp] = temp;
        }
        return -1;
    }

    // 进阶题目.要求不能改变原数组，采用二分法
    public int solve2(int[] arr) {

        int start = 1, end = arr.length-1;
        while (start <= end) {
            int middle = ((end-start) >> 1) + start;
            int count = count(arr, start, middle);
            if (start == end) {
                if (count > 1) return start;
                break;
            }
            if (count > middle-start+1) {
                end = middle;
            }else {
                start = middle+1;
            }
        }
        return -1;
    }

    private int count(int[] arr, int l, int h) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= l && arr[i] <= h) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,0,2,5,3};
        int[] arr2 = {2,3,4,0,1,5,6};
        System.out.println(solve(arr));
        System.out.println(solve(arr2));
    }
}
