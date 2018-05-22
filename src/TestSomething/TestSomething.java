package TestSomething;

public class TestSomething {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
//        String s = "abc";
//        String a = "a" + "b" + "c";
//        if (s == a) {
//            System.out.println("true");
//        }

//        String ss = new String("a") + new String("b");
//        ss.intern();
//        String aa = "ab";
//        if (ss == aa) {
//            System.out.println(true);
//        }
        int num = tableSizeFor(16);
        System.out.println(num);
    }
}
