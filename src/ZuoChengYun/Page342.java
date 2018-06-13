package ZuoChengYun;

/**
 * 无序数组，找到需要排序的最短子数组长度
 * end为右边界，从左向右遍历，记录出现的最大值，当出现arr[i] < max时，将end赋值为i
 * start为左边界，从右向左遍历，记录出现的最小值，当出现arr[i] < min时，将start赋值为i；
 */
public class Page342 {

    public int solve(int[] arr) {
        int start = 0, end = 0;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            else if (arr[i] < max) {
                end = i;
            }
        }
        int min = arr[arr.length-1];
        for (int j = arr.length-2; j >= 0; j--) {
            if (arr[j] < min) {
                min = arr[j];
            }
            else if (arr[j] > min) {
                start = j;
            }
        }
        return start == end ? -1 : end-start+1;
    }

    public static void main(String[] args) {
        Page342 instance = new Page342();

        int[] arr = {1,5,3,4,2,6,7};
        System.out.println(instance.solve(arr));

        int[] arr1 = {1,1,5,5,3,3,4,2,2,6,7};
        System.out.println(instance.solve(arr1));

        int[] arr2 = {1,2,3,4,5};
        System.out.println(instance.solve(arr2));

        int[] arr3 = {3,4,1,2,5,6};
        System.out.println(instance.solve(arr3));

        int[] arr4 = {3,4,1,2,6,5};
        System.out.println(instance.solve(arr4));
    }
}
