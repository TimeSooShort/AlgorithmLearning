package liuyubobo.heap;

import liuyubobo.SortTestHelper;

public class HeapSort1 {

    //构建堆与取出最大数组成数组两个过程复杂度均为O(nlogn)
    public static void sort(Comparable[] arr){
        int n = arr.length;
        Maxheap<Comparable> maxheap = new Maxheap<>(n);
        for (int i = 0; i < n; i++){
            maxheap.insert(arr[i]);
        }
        for (int j = n-1; j >= 0; j--){
            arr[j] = maxheap.extraMax();
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.randomArray(n, 0, n);
        SortTestHelper.testSort("liuyubobo.heap.HeapSort1", arr);
    }
}
