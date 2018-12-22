package Offer;

/**
 * 数组中个数超过一半的数字
 */
public class Offer39 {

    public int solve(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("array can't be null or empty");
        int num = arr[0];
        int numLen = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == num) {
                numLen++;
            }
            else {
                numLen--;
                if (numLen == 0) {
                    num = arr[i];
                    numLen = 1;
                }
            }
        }
        if (numLen > 1) return num;
        numLen = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) numLen++;
        }
        if (numLen > arr.length/2) return num;
        else throw new RuntimeException("no such number");
    }

    public static void main(String[] args) {
        Offer39 instance = new Offer39();
        int[] arr = {2,2,2,4,2,5,5,2,2,5,2,7,5};
        System.out.println(instance.solve(arr));
    }
}
