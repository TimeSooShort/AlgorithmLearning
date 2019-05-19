package Other;

import java.util.Arrays;
import java.util.List;

public class TestArrays {

    public static void main(String[] args) {
        int[] arr0 = {2,5,6,7,1283};
        List list0 = Arrays.asList(arr0);
        System.out.println(list0.contains(1283));
        System.out.println(list0.get(0).getClass().getName());

        Integer[] arr = {2,5,6,7,1283};
        List list = Arrays.asList(arr);
        System.out.println(list.contains(1283));

        System.out.println("=================");

        Object ob = 1234;
        Integer in = 1234;
        Integer ins = new Integer(1234);
        System.out.println(ob == in);
        System.out.println(ob == ins);
        System.out.println(ins == in);
        System.out.println(ob.equals(in));
        System.out.println(ob.equals(ins));
        System.out.println(ob == (Object)in);
        System.out.println(ob == (Object)ins);
        System.out.println(ob.getClass().getName());

        System.out.println("===============");

        Integer num = new Integer(45);
        System.out.println(num == 45);
        Integer a1 = 128;
        Integer a2 = 128;
        System.out.println(a1 == a2);
    }
}
