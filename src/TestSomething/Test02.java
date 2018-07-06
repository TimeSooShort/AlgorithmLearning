package TestSomething;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class Test02<K> {

    private Comparator<K> comparator;

    public Test02(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public Test02() {
    }
    @SuppressWarnings("unchecked")
    final int compare(Object k1, Object k2) {
        return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2)
                : comparator.compare((K)k1, (K)k2);
    }

    public void test(Object key) {
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
    }

    class Some {
        Some() {
        }
        int a;
        int b;
    }

    public static void main(String[] args) {
        Test02 instance = new Test02();
//        System.out.println(instance.compare(null, null));

        TreeMap<String, String> map = new TreeMap<>();
        map.put("c", "d");
//        map.put(null, "a");
        map.put("a", null);
        map.put("s", null);
//        System.out.println(map.get("s"));

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "a");
        hashMap.put(null, "d");
//        System.out.println(hashMap.get(null));

        Test02.Some ins = instance.new Some();
        instance.test(ins);
    }
}
