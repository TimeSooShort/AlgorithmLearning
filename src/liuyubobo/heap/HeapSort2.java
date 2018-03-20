package liuyubobo.heap;

import liuyubobo.SortTestHelper;

public class HeapSort2 {

    //优化构建堆的过程为O(n)
    public static void sort(Comparable[] arr){
        Maxheap<Integer> maxheap = new Maxheap<>(arr);
        for (int i = arr.length-1; i >= 0; i--){
            arr[i] = maxheap.extraMax();
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort2", arr);
    }
}
