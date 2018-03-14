package liuyubobo;

public class BubbleSort {

    public static void sort(Comparable[] arr){

//        boolean notOver;
//        do {
//            notOver = false;
//            for (int i = 1; i < arr.length; i++){
//                if (arr[i].compareTo(arr[i-1]) < 0){
//                    swap(arr, i, i-1);
//                    notOver = true;
//                }
//            }
//        }while(notOver);

        int n = arr.length;
        int upIndex;
        do {
            upIndex = 0;
            for (int i = 1; i < n; i++){
                if (arr[i].compareTo(arr[i-1]) < 0){
                    swap(arr, i, i-1);
                    upIndex = i;
                }
            }
            n = upIndex;
        }while(upIndex > 0);
    }

    private static void swap(Object[] a, int i, int j){
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = SortTestHelper.randomArray(20000, 0, 100000);
        SortTestHelper.testSort("liuyubobo.BubbleSort", arr);
    }
}
