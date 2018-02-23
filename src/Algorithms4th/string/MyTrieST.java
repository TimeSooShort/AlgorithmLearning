package Algorithms4th.string;


/**
 * 该算法不适于处理来自大型字母表的大量长建，空间消耗接近RNw（w为键长）
 */
public class MyTrieST<Value> {
    private static final int R = 256;

    private Node root;
    private int n;

    private static class Node{
        private Object value;
        private Node[] next = new Node[R];
    }

    public Value get(String key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        Node result = get(root, key, 0);
        if (result == null) return null;
        return (Value) result.value;
    }
    private Node get(Node x, String key, int d){
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public boolean contains(String key){
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return size() == 0;
    }

    public Iterable<String> keys(){
        return keysWithPrefix("");
    }
    public Iterable<String> keysWithPrefix(String prefix){
        Queue<String> queue = new Queue<>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), queue);
        return queue;
    }
    private void collect(Node x, StringBuilder prefix, Queue<String> result){
        if (x == null) return;
        if (x.value != null) result.equeue(prefix.toString());
        for (char r = 0; r < R; r++){
            prefix.append(r);
            collect(x.next[r], prefix, result);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    //通配符"."代表任意一个字母
    public Iterable<String> keysThatMatch(String pattern){
        Queue<String> queue = new Queue<>();
        collect(root, new StringBuilder(), pattern, queue);
        return queue;
    }
    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> result){
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.value != null) result.equeue(prefix.toString());
        if (d == pattern.length()) return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (int r = 0; r < R; r++){
                prefix.append(r);
                collect(x.next[r], prefix, pattern, result);
                prefix.deleteCharAt(prefix.length()-1);
            }
        }else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, result);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    public String longextPrefixOf(String query){
        if (query == null) throw new IllegalArgumentException("query is null");
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        return query.substring(0, length);
    }
    private int longestPrefixOf(Node x, String query, int d, int length){
        if (x == null) return length;
        if (x.value != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

    public void put(String key, Value value){
        if (key == null) throw new IllegalArgumentException("key to put() is null");
        if (value == null) delete(key);
        root = put(root, key, 0, value);
    }
    private Node put(Node x, String key, int d, Value value){
        if (x == null) x = new Node();
        if (d == key.length()){
            if (x.value == null) n++;
            x.value = value;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, d+1, value);
        return x;
    }

    public void delete(String key){
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        root = delete(root, key, 0);
    }
    //注意非空值与非空节点不能删除
    private Node delete(Node x, String key, int d){
        if (x == null) return null;
        if (d == key.length()){
            if (x.value != null) n--;
            x.value = null;
        } else{
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        if (x.value != null) return x;
        for (int r = 0; r < R; r++){
            if (x.next[r] != null) return x;
        }
        return null;
    }
}
