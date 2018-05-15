package liuyubobo.BinarySearchTree.binaryST;

public class BST<Key extends Comparable<Key>, Value> {

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    private Node root;
    private int count;

    public BST() {
        root = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    //插入操作
    public void insert(Key key, Value value) {
        root = insert(key, value, root);
    }

    //判断键是否存在
    public boolean contain(Key key) {
        return contain(key, root);
    }

    //获取value
    public Value get(Key key) {
        return get(key, root);
    }

    //-----------------辅助函数-------------------------

    //插入操作，递归，返回根节点
    private Node insert(Key key, Value value, Node node) {
        if (node == null) {
            ++count;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        }else if (key.compareTo(node.key) > 0) {
            node.right = insert(key, value, node.right);
        }else {
            node.left = insert(key, value, node.left);
        }
        return node;
    }

    //判断键是否存在
    private boolean contain(Key key, Node node) {
        if (node == null) {
            return false;
        }

        if (key.compareTo(node.key) == 0) {
            return true;
        }else if (key.compareTo(node.key) < 0) {
            return contain(key, node.left);
        }else {
            return contain(key, node.right);
        }
    }

    //获取value
    private Value get(Key key, Node node) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            return node.value;
        }else if (key.compareTo(node.key) < 0) {
            return get(key, node.left);
        }else {
            return get(key, node.right);
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        //打乱
        for (int j = 0; j < n; j++) {
            int pos = (int) (Math.random() * (j+1));
            int temp = arr[pos];
            arr[pos] = arr[j];
            arr[j] = temp;
        }

        BST<Integer, String> bst = new BST<>();
        for (int k = 0; k < n; k++) {
            bst.insert(arr[k], arr[k].toString()); //(key,value)——>(i, Integer.toString(i))
        }

        boolean result = true;
        for (int i = 0; i < 2*n; i++) {
            String val = bst.get(i);
            if (i < n) {
                if (!val.equals(Integer.toString(i))){
                    result = false;
                    break;
                }
            }else {
                if (val != null) {
                    result = false;
                    break;
                }
            }

        }
        if (result) {
            System.out.println("Success");
        }else {
            System.out.println("Fail");
        }
    }

}