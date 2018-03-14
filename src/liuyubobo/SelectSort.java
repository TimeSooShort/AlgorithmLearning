package liuyubobo;

public class SelectSort {

    public static void sort(Comparable[] a){
//        for (int i = 0; i < a.length; i++) {
//            int minIndex = i;
//        for (int j = i+1; j < a.length; j++) {
//            if (a[j].compareTo(a[minIndex]) < 0) {
//                minIndex = j;
//            }
//        }
//        swap(a, i, minIndex);
//    }

        //优化，在一轮循环中找到最大与最小值
        int left = 0, right = a.length-1;
        while (left < right){
            int minIndex = left, maxIndex = right;
            if (a[minIndex].compareTo(a[maxIndex]) > 0){
                swap(a, minIndex, maxIndex);
            }
            for (int i = left+1; i < right; i++){
                if (a[i].compareTo(a[minIndex]) < 0){
                    minIndex = i;
                } else if (a[i].compareTo(a[maxIndex]) > 0){
                    maxIndex = i;
                }
            }
            swap(a, minIndex, left);
            swap(a, maxIndex, right);
            left++;
            right--;
        }
    }

    private static void swap(Object[] a, int i, int j){
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        // int[] a = {4,6,13,17,33,2,22,7,3,19,9,8,22,15};
        // SelectSort.sort(a);
        // for(int i : a){
        // 	System.out.print(i);
        // 	System.out.print(" ");
        // }
        // System.out.println();

//		Integer[] a = {10,9,8,7,6,5,4,3,2,1};
//        SelectSort.sort(a);
//        for( int i = 0 ; i < a.length ; i ++ ){
//            System.out.print(a[i]);
//            System.out.print(' ');
//        }
//        System.out.println();
//
//        // 测试Double
//        Double[] b = {4.4, 3.3, 2.2, 1.1};
//        SelectSort.sort(b);
//        for( int i = 0 ; i < b.length ; i ++ ){
//            System.out.print(b[i]);
//            System.out.print(' ');
//        }
//        System.out.println();
//
//        // 测试String
//        String[] c = {"D", "C", "B", "A"};
//        SelectSort.sort(c);
//        for( int i = 0 ; i < c.length ; i ++ ){
//            System.out.print(c[i]);
//            System.out.print(' ');
//        }
//        System.out.println();

        Integer[] array = SortTestHelper.randomArray(20000, 0, 100000);
        SortTestHelper.testSort("liuyubobo.SelectSort", array);
    }
}
