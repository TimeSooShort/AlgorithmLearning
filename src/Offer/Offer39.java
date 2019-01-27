package Offer;

/**
 * 数组中个数超过一半的数字
 */
public class Offer39 {

    public static void solve(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("array can't be null or empty");
        int num = arr[0];
        int times = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == num) {
                times++;
            }
            else {
                times--;
                if (times == 0) {
                    num = arr[i];
                    times = 1;
                }
            }
        }
        times = 0;
        for (int anArr : arr) {
            if (num == anArr) times++;
        }
        if (times > arr.length/2) System.out.println(num);
        else System.out.println("No such number");
    }

    public static void main(String[] args) {
        int[] arr = {2,2,2,4,2,5,5,5,2,7,5};
        solve(arr);
    }
}
