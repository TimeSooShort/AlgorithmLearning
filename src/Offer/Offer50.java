package Offer;

import java.util.ArrayDeque;

public class Offer50 {
    public static int FirstNotRepeatingChar(String str) {
        if(str == null || str.length() == 0) return -1;
        int[] count = new int[256];
        char[] arr = str.toCharArray();
        for(char c: arr) {
            count[c]++;
        }
        int index = 0;
        for(char c : arr) {
            if(count[c] == 1) return index;
            index++;
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
