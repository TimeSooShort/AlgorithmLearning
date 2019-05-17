package Other;

import java.util.Scanner;

public class BitCount {
    public static BitCount b2 = new BitCount("b2");

    private static String name;

    public BitCount(String name) {
        this.name = name;
    }

    {
        System.out.println(name+"非静态代码块");
    }

    static {
        System.out.println(name+"静态代码块");
    }

    public static void main(String[] args) {
        BitCount b1 = new BitCount("b1");
    }
}
