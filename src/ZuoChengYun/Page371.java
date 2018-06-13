package ZuoChengYun;

/**
 * 在数组中找到局部最小值，不含重复元素，局部最小指：1，arr[0] < arr[1]，arr[0]局部最小
 * 2, arr[N] < arr[N-1] ，arr[N]局部最小 3, arr[i-1] > arr[i] < arr[i+1]，arr[i]局部最小
 * 采用二分法
 */
public class Page371 {

    public int solve(int[] arr) {
        int left = 0, right = arr.length-1;

        if (arr[left] < arr[left+1]) return arr[left];
        else if (arr[right] < arr[right-1]) return arr[right];

        while (left <= right) {
            int mid = left + (right-left)/2;

            if (arr[mid] > arr[mid-1]) right = mid;
            else if (arr[mid] > arr[mid+1]) left = mid;
            else return arr[mid];
        }
        return -1;
    }

    public static void main(String[] args) {
        Page371 instance = new Page371();

        int[] arr = {6,5,3,4,2,7};
        System.out.println(instance.solve(arr));

        int[] arr1 = {5,6,7};
        System.out.println(instance.solve(arr1));

        int[] arr2 = {7,6,5};
        System.out.println(instance.solve(arr2));
    }
}
