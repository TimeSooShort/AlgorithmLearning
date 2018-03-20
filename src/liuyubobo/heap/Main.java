package liuyubobo.heap;

import liuyubobo.SortTestHelper;

import java.util.Arrays;

public class Main {

    // 比较 Merge Sort, 三种 Quick Sort 和本节介绍的两种 Heap Sort 的性能效率
    // 注意, 这几种排序算法都是 O(nlogn) 级别的排序算法
    public static void main(String[] args) {
        int n = 1000000;

        //一般情况
        System.out.println("一般情况");
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr, n);
        Integer[] arr3 = Arrays.copyOf(arr, n);
        Integer[] arr4 = Arrays.copyOf(arr, n);
        Integer[] arr5 = Arrays.copyOf(arr, n);
        Integer[] arr6 = Arrays.copyOf(arr, n);
        Integer[] arr7 = Arrays.copyOf(arr, n);


        SortTestHelper.testSort("liuyubobo.merge.MergeSort", arr);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort", arr2);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort2Ways", arr3);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort3Ways", arr4);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort1", arr5);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort2", arr6);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort", arr7);

        //近乎有序
        System.out.println("近乎有序");
        arr = SortTestHelper.neralyOrderedArr(n, 100);
        arr2 = Arrays.copyOf(arr, n);
        arr3 = Arrays.copyOf(arr, n);
        arr4 = Arrays.copyOf(arr, n);
        arr5 = Arrays.copyOf(arr, n);
        arr6 = Arrays.copyOf(arr, n);
        arr7 = Arrays.copyOf(arr, n);

        SortTestHelper.testSort("liuyubobo.merge.MergeSort", arr);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort", arr2);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort2Ways", arr3);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort3Ways", arr4);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort1", arr5);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort2", arr6);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort", arr7);

        //大量重复元素
        System.out.println("大量重复元素");
        arr = SortTestHelper.randomArray(n, 0, 10);
        arr2 = Arrays.copyOf(arr, n);
        arr3 = Arrays.copyOf(arr, n);
        arr4 = Arrays.copyOf(arr, n);
        arr5 = Arrays.copyOf(arr, n);
        arr6 = Arrays.copyOf(arr, n);
        arr7 = Arrays.copyOf(arr, n);

        SortTestHelper.testSort("liuyubobo.merge.MergeSort", arr);
        //SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort", arr2);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort2Ways", arr3);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort3Ways", arr4);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort1", arr5);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort2", arr6);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort", arr7);
    }
}
