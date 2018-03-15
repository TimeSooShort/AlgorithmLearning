package liuyubobo;

import java.util.Arrays;

public class TestInsertAndMerge {

    public static void main(String[] args) {

        //一般情况下
        int n = 100000;
        System.out.println("一般情况下测试，数组大小: "+ n+ "范围[0, "+ n +"]");

        Integer[] arr1 = SortTestHelper.randomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, n);

        SortTestHelper.testSort("liuyubobo.InsertSort", arr1);
        SortTestHelper.testSort("liuyubobo.MergeSort", arr2);


        //近乎有序的数组
        // 对于近乎有序的数组, 数组越有序, InsertSort的时间性能越趋近于O(n)
        // 所以可以尝试, 当swapTimes比较大时, MergeSort更快
        // 但是当swapTimes小到一定程度, InsertionSort变得比MergeSort快
        System.out.println("近乎有序的数组测试，数组大小: "+ n+ "范围[0, "+ n +"]");

        arr1 = SortTestHelper.neralyOrderedArr(n, 100);
        arr2 = Arrays.copyOf(arr1, n);

        SortTestHelper.testSort("liuyubobo.InsertSort", arr1);
        SortTestHelper.testSort("liuyubobo.MergeSort", arr2);
    }
}
