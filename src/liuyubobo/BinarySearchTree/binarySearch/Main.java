package liuyubobo.BinarySearchTree.binarySearch;

public class Main {

    /**
     * 十万数量级时，递归好于非递归
     * 一百万时，非递归好于递归
     * @param args
     */
    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer(i);
        }

        BinarySearch search1 = new BinarySearch();
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < n*2; j++) {
            int r = search1.find(arr, new Integer(j));
            if (j < n) {
                assert r == j;
            }
            else {
                assert r == -1;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("非递归耗时：" + (endTime-startTime));

        BinarySearch2 search2 = new BinarySearch2();
        startTime = System.currentTimeMillis();
        for (int j = 0; j < n*2; j++) {
            int r = search2.find(arr, new Integer(j));
            if (j < n) {
                assert r == j;
            }
            else {
                assert r == -1;
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("递归耗时：" + (endTime-startTime));
    }
}
