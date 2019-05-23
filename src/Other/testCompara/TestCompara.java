package Other.testCompara;

import java.util.HashMap;

public class TestCompara extends AAClass{
    public static void main(String[] args) {
        TestCompara ins = new TestCompara();
//        AAClass aa = new AAClass();
//        System.out.println(aa.getClass() == ins.getClass());
        HashMap<Object, Integer> map = new HashMap<>();
        map.put(null, 1);
        System.out.println(map.get(null));
        System.out.println(ins.getClass().getName());
        System.out.println(System.identityHashCode(null));
    }
}
