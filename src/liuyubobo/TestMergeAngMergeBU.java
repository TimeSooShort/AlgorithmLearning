package liuyubobo;

import java.util.Arrays;

public class TestMergeAngMergeBU {

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] arr1 = SortTestHelper.randomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, n);

        SortTestHelper.testSort("liuyubobo.merge.MergeSort", arr1);
        SortTestHelper.testSort("liuyubobo.merge.MergeSortBU", arr2);

        arr1 = SortTestHelper.neralyOrderedArr(n, 100);
        arr2 = Arrays.copyOf(arr1, n);

        SortTestHelper.testSort("liuyubobo.merge.MergeSort", arr1);
        SortTestHelper.testSort("liuyubobo.merge.MergeSortBU", arr2);
    }
}
