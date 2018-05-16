package liuyubobo.BinarySearchTree.binaryST;


import java.util.LinkedList;
import java.util.Queue;

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

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }
    //中序遍历
    public void inOrder() {
        inOrder(root);
    }
    //后序遍历
    public void postOrder() {
        postOrder(root);
    }

    //层级遍历
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.key + " ");
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public Key minimum() {
        assert count != 0;
        Node min = minimum(root);
        return min.key;
    }

    public Key maximum() {
        assert count != 0;
        Node max = maximum(root);
        return max.key;
    }

    //删除节点
    public void remove(Key key) {
        root = remove(root, key);
    }

    //删除最小节点
    public void removeMin() {
        if (root != null) {
            root = removeMin(root);
        }
    }

    //删除最大节点
    public void removeMax() {
        if (root != null) {
            root = removeMax(root);
        }
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

    //前序遍历
    private void preOrder(Node node){
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //中序遍历
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    //后序遍历
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    //最小节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //最大节点
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    //删除最小节点
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            --count;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    //删除最大节点
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            --count;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    //删除节点
    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        }else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        }else {
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                --count;
                return right;
            }

            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                --count;
                return left;
            }

            Node minRight = minimum(node.right);
            node.key = minRight.key;
            node.value = minRight.value;
            node.right = removeMin(node.right);  //这里注意：因为有可能右子树只有一个，所以需要对返回结果重新赋值
        }
        return node;
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
