package Offer;

/**
 * 判断数组是否是某颗二叉查找树的后续遍历，数组无重复重复元素
 */
public class Offer33 {

    public boolean solve(int[] arr) {
        if (arr == null || arr.length == 0) return false;
        return process(arr, 0, arr.length-1);
    }

    private boolean process(int[] arr, int start, int end) {
        if (start == end || start + 1 == end) return true;
        int less = -1; // 代表最大（指下标最大）小于根节点（arr[end])的位置
        int more = end; // 代表最小（指下标最小）大于根节点（arr[end])的位置
        for (int i = start; i < end; i++) {
            if (arr[end] > arr[i]) {
                less = i;
            }else {
                more = more == end ? i : more;
            }
        }
        if (less == -1 || more == end) {
            return process(arr, start, end-1);
        }
        if (less != more-1) {
            return false;
        }
        return process(arr, start, less) && process(arr, more, end-1);
    }

    // 进阶题目：后序数组重建二叉查找树
    public TreeNode rebuildBST(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        return processRe(arr, 0, arr.length-1);
    }

    private TreeNode processRe(int[] arr, int start, int end) {
        if (start == end) return new TreeNode(arr[start]);
        TreeNode head = new TreeNode(arr[end]);
        int more = end;
        for (int i = start; i < end; i++) {
            if (arr[end] < arr[i]) {
                more = i;
                break;
            }
        }
        head.left = processRe(arr, start, more-1);
        head.right = processRe(arr, more, end-1);
        return head;
    }
}
