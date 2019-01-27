package Offer;

public class LeetCode16 {

    public static double power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        if (exponent == -1) return 1/base;
        double result = power(base*base, exponent >> 1);
        if ((exponent & 1) == 1) result *= base;
        return result;
    }

    public static void main(String[] args) {
        double base = -4;
        int exponent = -4;
        System.out.println(power(base, exponent));
        System.out.println(Math.pow(base, exponent));
        System.out.println(power(base, exponent) == Math.pow(base, exponent));
    }
}
