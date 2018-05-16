package liuyubobo.BinarySearchTree.binaryST;

public class Main {

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        int n = 10;
        int m = 100;
        for (int i = 0; i < n; i++) {
            int pos = (int) (Math.random() * m);
            bst.insert(pos, pos);
            System.out.print(pos + ", ");
        }
        System.out.println();

        //测试size
        System.out.println("size : " + bst.size());

        //测试前序遍历
        System.out.println("preOrder: ");
        bst.preOrder();
        System.out.println();

        //测试中序
        System.out.println("inOrder: ");
        bst.inOrder();
        System.out.println();

        //测试后序
        System.out.println("postOrder: ");
        bst.postOrder();
        System.out.println();

        //测试层级遍历
        System.out.println("levelOrder: ");
        bst.levelOrder();
        System.out.println();
    }
}
