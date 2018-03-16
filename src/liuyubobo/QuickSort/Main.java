package liuyubobo.QuickSort;

import liuyubobo.SortTestHelper;

import java.util.Arrays;

// 比较Merge Sort和双路快速排序和三路快排三种排序算法的性能效率
// 对于包含有大量重复数据的数组, 三路快排有巨大的优势
// 对于一般性的随机数组和近乎有序的数组, 三路快排的效率虽然不是最优的, 但是是在非常可以接受的范围里
// 因此, 在一些语言中, 三路快排是默认的语言库函数中使用的排序算法。比如Java:)
public class Main {

    public static void main(String[] args) {
        int n = 1000000;

        //一般情况下，快排由于归并排序;三种快排的表现相差不大；
        System.out.println("for normal situation :" + "arr.length=" + n);

        Integer[] arr1 = SortTestHelper.randomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, n);
        Integer[] arr3 = Arrays.copyOf(arr1, n);

        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort", arr1);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort3Ways", arr3);

        //对于近乎有序的数组，快排算法退化成O(n^2)及级别,在进行优化平衡后，快排表现优于归并；
        //面对大量数据不建议使用，推荐使用二向，三向快排，二向由于三向
        System.out.println("nearly sorted array :" + "array size:" + n);
        arr1 = SortTestHelper.neralyOrderedArr(n, 100);
        arr2 = Arrays.copyOf(arr1, n);
        arr3 = Arrays.copyOf(arr1, n);


        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort3Ways", arr3);

        //对于含有大量重复元素是数组，快排需要进一步优化，优化后的QuickSort2Ways解决了这一问题
        //此种情况三向优于二向
        System.out.println("has more repeated item array: " + "array size: "+ n);
        arr1 = SortTestHelper.randomArray(n , 0, 20);
        arr2 = Arrays.copyOf(arr1, n);
        arr3 = Arrays.copyOf(arr1, n);


        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort2Ways", arr2);
        SortTestHelper.testSort("liuyubobo.QuickSort.QuickSort3Ways", arr3);
    }
}
