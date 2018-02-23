package Algorithms4th.string;


/**
 * Created by Administrator on 2018/2/23.
 * 三向单词查找树中，字符是显示的保存在节点中，只有在沿着中间链前进时才会找到对应的键。
 */
public class MyTST<Value> {
    private int n;
    private Node<Value> root;

    private static class Node<Value>{
        private Value value;
        private char c;
        private Node<Value> left, middle, right;
    }

    public Value get(String key){
        if (key == null) throw new IllegalArgumentException("key to get() is null");
        if (key.length() == 0) throw new IllegalArgumentException("key is empty");
        Node<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.value;
    }
    private Node<Value> get(Node<Value> x, String key, int d){
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length()-1) return get(x.middle, key, d+1);
        else return x;
    }

    public boolean contains(String key){
        if (key == null) throw new IllegalArgumentException("key to contains() is null");
        return get(key) != null;
    }

    public void put(String key, Value val){
        if (key == null) throw new IllegalArgumentException("key to put() is null");
        if (!contains(key)) n++;
        root = put(root, key, val, 0);
    }
    private Node<Value> put(Node<Value> x, String key, Value val, int d){
        char c = key.charAt(d);
        if (x == null){
            x = new Node<>();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length()-1) x.middle = put(x.middle, key, val, d+1);
        else x.value = val;
        return x;
    }

    public String longestPrefixOf(String query){
        if (query == null) throw new IllegalArgumentException("key to longestPrefixOf() is null");
        if (query.length() == 0) return null;
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        return query.substring(0, length);
    }
    private int longestPrefixOf(Node x, String key, int d, int length){
        if (x == null) return length;
        char c = key.charAt(d);
        if (c < x.c) length = longestPrefixOf(x.left, key, d, length);
        else if (c > x.c) length = longestPrefixOf(x.right, key, d, length);
        else if (d < key.length()-1){
            if (x.value != null) length = d+1;
            length = longestPrefixOf(x.middle, key, d+1, length);
        }
        else length = key.length();
        return length;
    }

    public static void main(String[] args) {
        String[] a = {"she", "sells", "shells", "sea", "by", "the","sea", "shea"};
        MyTST<Integer> tst = new MyTST<>();
        int i = 0;
        for (String s : a){
            tst.put(s, i++);
        }
        String result = tst.longestPrefixOf("shealafs");
        System.out.println(result);
    }
}
