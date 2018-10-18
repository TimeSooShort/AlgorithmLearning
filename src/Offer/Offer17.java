package Offer;

import java.util.Arrays;

public class Offer17 {

    public void solve(int n) {

        byte[] arr = new byte[n];
        for (int i = 0; i < 10; i++) {
            arr[0] = (byte) (i + '0');
            print(arr, n, 1);
        }
    }

    private void print(byte[] arr, int n, int index) {
        if (n == index) {
            System.out.print(new String(arr) + " ");
            return;
        }
        for (int i = 0; i < 10; i++) {
            arr[index] = (byte) (i + '0');
            print(arr, n, index+1);
        }
    }

    public static void main(String[] args) {
        Offer17 instance = new Offer17();
        instance.solve(2);
    }
}
