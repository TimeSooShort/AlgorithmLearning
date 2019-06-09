package Offer;

public class Offer65 {

    private static int solve(int a, int b) {
        return b == 0 ? a : solve(a^b, (a&b)<<1);
    }

    public static void main(String[] args) {
        System.out.println(solve(577, 348));
    }
}
