package liuyubobo;

import java.util.Arrays;

public class Main {

    //优化后的插入排序性能好于选择排序
    //数组有序性越强，插入排序变现越好
    public static void main(String[] args) {
        int n = 20000;

        //一般测试
        System.out.println("测试选择与插入排序,数组大小：" + n + "范围从：[0, " + n +")");

        Integer[] arr = SortTestHelper.randomArray(n ,0, n);
        Integer[] arrCopy = Arrays.copyOf(arr, n);

        SortTestHelper.testSort("liuyubobo.SelectSort", arr);
        SortTestHelper.testSort("liuyubobo.InsertSort", arrCopy);

        //有序性很强的数组
        System.out.println("测试有序性更强的数组，大小：" + n + "范围从：[0, 3)");
        arr = SortTestHelper.randomArray(n ,0, 3);
        arrCopy = Arrays.copyOf(arr, n);

        SortTestHelper.testSort("liuyubobo.SelectSort", arr);
        SortTestHelper.testSort("liuyubobo.InsertSort", arrCopy);

        //近乎有序的数组
        int swapTime = 100;
        System.out.println("测试近乎有序的数组数组，大小：" + n + "交换次数：" + swapTime);

        arr = SortTestHelper.neralyOrderedArr(n, swapTime);
        arrCopy = Arrays.copyOf(arr, n);

        SortTestHelper.testSort("liuyubobo.SelectSort", arr);
        SortTestHelper.testSort("liuyubobo.InsertSort", arrCopy);
    }
}
