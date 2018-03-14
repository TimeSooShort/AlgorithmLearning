package liuyubobo;

/**
 * 希尔排序是四种排序中最优的
 */
public class ShellSort {

    public static void sort(Comparable[] arr){
        int n = arr.length;

        int h = 1;
        while (h < n/3) h = h*3 + 1;

        while (h >= 1){
            for (int i = h; i < n; i++){
                Comparable e = arr[i];
                int j = i;
                for (; j >= h && e.compareTo(arr[j-h]) < 0; j -= h){
                    arr[j] = arr[j-h];
                }
                arr[j] = e;
            }
            h /= 3;
        }
    }

    private static void swap(Object[] a, int i, int j){
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = SortTestHelper.randomArray(20000, 0, 100000);
        SortTestHelper.testSort("liuyubobo.ShellSort", arr);
    }
}
