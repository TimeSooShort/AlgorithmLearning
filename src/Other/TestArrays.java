package Other;

import java.util.Arrays;
import java.util.List;

public class TestArrays {

    public static void main(String[] args) {
        Integer[] arr = {2,5,6,7,8};
        List list = Arrays.asList(arr);
        System.out.println(list.contains(2));
        Integer num = new Integer(45);
        System.out.println(num == 45);
        Integer a1 = 128;
        Integer a2 = 128;
        System.out.println(a1 == a2);
    }
}
