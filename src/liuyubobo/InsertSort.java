package liuyubobo;

public class InsertSort {

    public static void sort(Comparable[] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = i; j > 0 && less(arr[j], arr[j-1]); j--){
                swap(arr, j, j-1);
            }
        }
    }

    private static void swap(Object[] a, int i, int j){
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        //469ms
        Integer[] array = SortTestHelper.randomArray(20000, 0, 100000);
        SortTestHelper.testSort("liuyubobo.InsertSort", array);
    }
}
