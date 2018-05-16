package liuyubobo.BinarySearchTree.binaryST;

import java.util.Arrays;

public class TestRemove {

    public static void shuffle(int[] arr) {
        for (int i = arr.length-1; i > 0; i--) {
            int random = (int) (Math.random() * (i+1)); //目的是避免重复交换
            int temp = arr[i];
            arr[i] = arr[random];
            arr[random] = temp;
        }
    }

    public static void main(String[] args) {
        int n = 20;
        BST<Integer, Integer> bst = new BST<>();

        for (int i = 0; i < n; i++) {
            int item = (int) (Math.random()*n);
            bst.insert(item, item);
        }
        //因为存在重复，所以size可能小于n
        System.out.println("size: " + bst.size());
        bst.levelOrder();
        System.out.println();

        int[] keys = new int[n];
        for (int i = 0; i < n; i++) {
            keys[i] = i;
        }
        shuffle(keys);//打乱
        System.out.print(Arrays.toString(keys));
        System.out.println();

        for (int i = 0; i < n; i++) {
            if (bst.contain(keys[i])) {
                bst.remove(keys[i]);
                System.out.println("After remove " + keys[i] + " size = " + bst.size());
            }
        }
        System.out.println("Finally the bst should be empty, size = " + bst.size());
    }
}
