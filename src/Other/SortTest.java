package Other;

import java.util.Arrays;
import java.util.Comparator;

public class SortTest {

    // 冒泡排序
    public void bubble(Comparable[] arr) {

    }

    // 选择排序
    public void select(Comparable[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            int minIndex = i;
//            for (int j = i+1; j < arr.length && less(arr[j], arr[minIndex]); j++) {
//                minIndex = j;
//            }
//            swap(arr, i, minIndex);
//        }

        int left = 0, right = arr.length-1;
        while (left < right) {
            int minIndex = left, maxIndex = right;
            if (less(arr[maxIndex], arr[minIndex])) swap(arr, minIndex, maxIndex);
            for (int i = left+1; i < right; i++) {
                if (less(arr[i], arr[minIndex])) {
                    minIndex = i;
                }else if (less(arr[maxIndex], arr[i])){
                    maxIndex = i;
                }
            }
            swap(arr, minIndex, left);
            swap(arr, maxIndex, right);
            left++;
            right--;
        }
    }

    // 插入排序
    public void insert(Comparable[] arr) {
        insert(arr, 0, arr.length-1);
    }

    private void insert(Comparable[] arr, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > lo && less(e, arr[j-1]); j--)
                arr[j] = arr[j-1];
            arr[j] = e;
        }
    }

    // Shell排序
    public void shell(Comparable[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n/3) h = h*3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                Comparable e = arr[i];
                int j = i;
                for (; j >= h && less(e, arr[j-h]); j -= h) {
                    arr[j] = arr[j-h];
                }
                arr[j] = e;
            }
             h /= 3;
        }
    }

    //堆排序
    public void heap(Comparable[] arr) {
        int n = arr.length;
        for (int i = n/2-1; i >= 0; i--) {
            shiftDown(arr, i, n);
        }
        for (int i = n-1; i > 0; i--) {
            swap(arr, i, 0);
            shiftDown(arr, 0, i);
        }
    }
    private void shiftDown(Comparable[] arr, int downIndex, int ln) {
        Comparable e = arr[downIndex];
        while (2*downIndex+1 < ln) {
            int j = 2*downIndex+1;
            if (j + 1 < ln && less(arr[j], arr[j+1])) j++;
            if (e.compareTo(arr[j]) >= 0) break;
            arr[downIndex] = arr[j];
            downIndex = j;
        }
        arr[downIndex] = e;
    }

    // ============================五种快排实现==========
    // 快速
    public void quick(Comparable[] arr) {
        quick(arr, 0, arr.length-1);
    }

    private void quick(Comparable[] arr, int lo, int hi) {
        if (lo + 15 >= hi) {
            // 插排
            insert(arr, lo, hi);
        }
        int pivot = partition(arr, lo, hi);
        quick(arr, lo, pivot-1);
        quick(arr, pivot+1, hi);
    }

    //该方法需要多次交换
    private int partition(Comparable[] arr, int lo, int hi) {
        swap(arr, lo, (int) (Math.random()*(hi-lo+1))+lo);
        Comparable pivot = arr[lo];
        int lt = lo+1, rt = hi;
        while (lt <= rt) {
            while (lt <= rt && less(arr[lt], pivot)) lt++;
            while (lt <= rt && !less(arr[rt], pivot)) rt--;
            if(lt > rt) break;
            swap(arr, lt, rt);
            lt++;
            rt--;
        }
        swap(arr, lo, rt);
        return rt;
    }


    // 针对上面代码，优化，省去交换
    private int partition2(Comparable[] arr, int lo, int hi) {
        swap(arr, lo, (int) (Math.random()*(hi-lo+1))+lo);
        Comparable e = arr[lo];
        int lt = lo, rt = hi;
        while (lt < rt) {
            while (lt < rt && less(e, arr[rt])) rt--; // e < arr[rt],从右向左找到第一个 <= e的值的位置
            arr[lt] = arr[rt];
            while (lt < rt && !less(e, arr[lt])) lt++; // e >= arr[lt]，从左向右找到第一个 > e的值的位置
            arr[rt] = arr[lt];
        }
        arr[lt] = e;
        return lt;
    }

    //basic
    private int partition3(Comparable[] arr, int lo, int hi) {
        swap(arr, lo, (int) (Math.random()*(hi-lo+1))+lo);
        Comparable e = arr[lo];
        int j = lo;
        for (int i = lo+1; i <= hi; i++) {
            if (less(arr[i], e)) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, j, lo);
        return j;
    }

    // 3 Way quick sort
    public void quick3Way(Comparable[] arr, int lo, int hi) {
        swap(arr, lo, (int) (Math.random()*(hi-lo+1))+lo);
        Comparable e = arr[lo];
        int lt = lo, gt = hi+1, k = lo+1;
        while (k < gt) {
            if (less(arr[k], e)) {
                swap(arr, ++lt, k++);
            }else if (less(e, arr[k])) {
                swap(arr, --gt, k);
            }else {
                k++;
            }
        }
        swap(arr, lo, lt);
        swap(arr, gt, hi);
    }

    //双轴快排
    public void dualPivot(Comparable[] arr, int lo, int hi) {
        if (lo+15 <= hi) {
            for (int i = lo; i <= hi; i++) {
                Comparable e = arr[i];
                int j = i;
                for (; j > lo && less(e, arr[j-1]); j--) {
                    arr[j] = arr[j-1];
                }
                arr[j] = e;
            }
        }
        if (less(arr[hi], arr[lo])) swap(arr, lo, hi);
        Comparable pivot1 = arr[lo];
        Comparable pivot2 = arr[hi];

        int lt = lo, rt = hi, k = lo+1;
        while (k < rt) {
            if (less(arr[k], pivot1)) swap(arr, ++lt, k++);
            else if (less(pivot2, arr[k])) swap(arr, --rt, k);
            else k++;
        }
        swap(arr, lo, lt);
        swap(arr, rt, hi);
        dualPivot(arr, lo, lt-1);
        dualPivot(arr, lt+1, rt-1);
        dualPivot(arr, rt+1, hi);
    }

    // 归并

    // 递归从上而下式
    public void mergeSort(Comparable[] arr) {
        int n = arr.length;
        Comparable[] aux = new Comparable[n];
        mergeSort(arr, aux, 0, n-1);
    }

    private void mergeSort(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        if (lo + 15 >= hi) {
            // 插排
            insert(arr, lo, hi);
        }
        int mid = (hi-lo)/2 + lo;
        mergeSort(arr, aux, lo, mid);
        mergeSort(arr, aux, mid+1, hi);
        if (less(arr[mid+1], arr[mid])) {
            merge(arr, aux, lo, mid, hi);
        }
    }

    private void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi-lo+1);

        int lt = lo, rt = mid+1;
        for (int i = lo; i <= hi; i++) {
            if (lt > mid) arr[i] = aux[rt++];
            else if (rt > hi) arr[i] = aux[lt++];
            else if (less(arr[lt], arr[rt])) arr[i] = aux[lt++];
            else arr[i] = aux[rt++];
        }
    }

    // 非递归，从下而上式
    public void mergeSortBU(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i += 16) {
            insert(arr, i, Math.min(n-1, i+15));
        }
        for (int sz = 16; sz < n; sz *= 2) {
            for (int lo = 0; lo < n-sz; lo += sz*2) {
                merge(arr, lo, lo+sz-1, Math.min(n-1, lo+sz*2-1));
            }
        }
    }

    private void merge(Comparable[] arr, int lo, int mid, int hi) {
        Comparable[] aux = Arrays.copyOfRange(arr, lo, hi+1);

        int lt = 0, auxMid = mid-lo, rt = mid-lo+1;
        for (int i = lo; i <= hi; i++) {
            if (lt > auxMid) arr[i] = aux[rt++];
            else if (rt > aux.length-1) arr[i] = aux[lt++];
            else if (less(aux[lt], aux[rt])) arr[i] = aux[lt++];
            else arr[i] = aux[rt++];
        }
    }

    private void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
