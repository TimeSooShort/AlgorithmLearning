package liuyubobo.merge;

import java.util.Arrays;

public class Main {

    // 比较 Merge Sort 和 Merge Sort BU 的性能效率
    // 使用更科学的比较方式, 每次比较都运行多次测试用例, 取平均值
    // 同时比较了优化和不优化两种情况
    // 总体来说, Merge Sort BU 比 Merge Sort 快一些。但优化后, 二者的性能差距不明显
    public static void main(String[] args) {
        int n = 1000000;
        long time1 = 0, time2 = 0;

        //未优化
        for (int i = 0; i < 100; i++){
            Integer[] arr1 = SortTestHelper.randomArray(n, 0, n);
            Integer[] arr2 = Arrays.copyOf(arr1, n);

            time1 += SortTestHelper.testSort("liuyubobo.merge.MergeSortUnAdvance", arr1);
            time2 += SortTestHelper.testSort("liuyubobo.merge.MergeBuUnAdvance", arr2);
        }
        System.out.println("未优化情况");
        System.out.println("merge unAdvanced average:" + time1/100 + "ms");
        System.out.println("mergeBu unAdvanced average: " + time2/100 + "ms" );
        System.out.println();

        //优化情况下
        time1 = 0;
        time2 = 0;
        for (int i = 0; i < 100; i++){
            Integer[] arr1 = SortTestHelper.randomArray(n, 0, n);
            Integer[] arr2 = Arrays.copyOf(arr1, n);

            time1 += SortTestHelper.testSort("liuyubobo.merge.MergeSort", arr1);
            time2 += SortTestHelper.testSort("liuyubobo.merge.MergeSortBU", arr2);
        }
        System.out.println("优化情况");
        System.out.println("merge average:" + time1/100 + "ms");
        System.out.println("mergeBu average: " + time2/100 + "ms" );
        System.out.println();
    }
}
