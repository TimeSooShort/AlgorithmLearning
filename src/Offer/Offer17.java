package Offer;

import java.util.Arrays;

public class Offer17 {

    public void solve(int n) {

        byte[] arr = new byte[n];
        print(arr, 0);
    }

    private void print(byte[] arr, int index) {
        if (arr.length == index) {
            System.out.print(new Integer(new String(arr)) +" ");
            return;
        }
        for (int i = 0; i < 10; i++) {
            arr[index] = (byte) (i + '0');
            print(arr, index+1);
        }
    }

    public static void main(String[] args) {
        Offer17 instance = new Offer17();
        instance.solve(2);
    }
}
