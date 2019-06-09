package Offer;

public class Offer64 {
    public static int specialSum(int n) {
        int sum = n;
        boolean a = n > 0 && (sum += specialSum(n-1)) > 0;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(specialSum(10));
    }
}
