package liuyubobo.QuickSort;

import liuyubobo.SortTestHelper;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int n = 1000000;

        //一般情况下，快排由于归并排序
        System.out.println("for normal situation :" + "arr.length=" + n);

        Integer[] arr1 = SortTestHelper.randomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, n);

        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort", arr1);
        SortTestHelper.testSort("liuyubobo.MergeSort", arr2);

        //对于近乎有序的数组，快排算法退化成O(n^2)及级别,在进行优化平衡后，快排表现优于归并；
        System.out.println("nearly sorted array :" + "array size:" + n);
        arr1 = SortTestHelper.neralyOrderedArr(n, 100);
        arr2 = Arrays.copyOf(arr1, n);

        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort", arr1);
        SortTestHelper.testSort("liuyubobo.MergeSort", arr2);

        //对于含有大量重复元素是数组，快排需要进一步优化，优化后的QuickSort2Ways解决了这一问题

    }
}
