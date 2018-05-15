package liuyubobo.BinarySearchTree.binarySearch;

/**
 * 非递归的二分法
 */
public class BinarySearch {

    public BinarySearch() {
    }

    public int find(Comparable[] arr, Comparable target){
        int lo = 0, hi = arr.length-1;
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;

            if (arr[mid].compareTo(target) == 0) {
                return mid;
            }else if (arr[mid].compareTo(target) > 0) {
                hi = mid-1;
            }else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
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
