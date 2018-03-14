package liuyubobo;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int n = 20000;
        System.out.println("测试选择与插入排序,数组大小：" + n + "范围从：[0, " + n +")");

        Integer[] arr = SortTestHelper.randomArray(n ,0, n);
        Integer[] arrCopy = Arrays.copyOf(arr, n);

        SortTestHelper.testSort("liuyubobo.SelectSort", arr); //344ms
        System.out.println();
        SortTestHelper.testSort("liuyubobo.InsertSort", arrCopy); //531ms
    }
}
