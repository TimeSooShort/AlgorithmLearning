package liuyubobo.BinarySearchTree.binarySearch;

public class BinarySearch2 {

    public BinarySearch2() {
    }

    public int find(Comparable[] arr, Comparable target) {
        return find(arr, target, 0, arr.length-1);
    }

    private int find(Comparable[] arr, Comparable target, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo)/2;
        if (arr[mid].compareTo(target) == 0) return mid;
        else if (arr[mid].compareTo(target) > 0) return find(arr, target, lo, mid-1);
        else return find(arr, target, mid+1, hi);
    }

    public static void main(String[] args) {
        BinarySearch2 search = new BinarySearch2();
        int n = 1000;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer(i);
        }
        boolean success = true;
        for (int j = 0; j < n*2; j++) {
            int r = search.find(arr, new Integer(j));
            if (j < n) {
                if (r != j) {
                    success = false;
                    break;
                }
            }
            else {
                if (r != -1) {
                    success = false;
                    break;
                }
            }
        }
        if (success) {
            System.out.println("通过");
        }else{
            System.out.println("错误");
        }
    }
}
