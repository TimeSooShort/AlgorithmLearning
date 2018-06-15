package TestSomething;

public class Test01 {

    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        return n;
    }

    static final int advanceProbe(int probe) {
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        return probe;
    }

    static final int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }

    public static void main(String[] args) {
//        int rs = numberOfLeadingZeros(4);
//        System.out.println(rs);
//        int rs = resizeStamp(16);
//        rs = (rs << 16) + 2;
//        System.out.println(rs);
//        System.out.println(Integer.toBinaryString(rs));

//        int res = advanceProbe(0x9e3779b9);
//        System.out.println(0x9e3779b9);
//        System.out.println(res);

        System.out.println(Integer.MAX_VALUE);
    }
}
