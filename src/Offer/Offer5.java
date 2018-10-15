package Offer;

import java.util.Arrays;

public class Offer5 {

    public String solve(String s) {
        if (s == null || s.length() == 0) return null;
        char[] sArr = s.toCharArray();
        int spaceCount = 0;
        for (char aSArr : sArr) {
            if (aSArr == ' ') {
                spaceCount++;
            }
        }
        int ln = sArr.length + spaceCount*2;
        char[] result = new char[ln];
        int t = ln-1;
        for (int i = sArr.length-1; i >= 0; i--) {
            if (sArr[i] == ' ') {
                result[t--] = '0';
                result[t--] = '2';
                result[t--] = '%';
            }else {
                result[t--] = sArr[i];
            }
        }
        return new String(result);
    }

    public static void main(String[] args) {
        String s = " We are family. ";
        Offer5 instance = new Offer5();
        System.out.println(instance.solve(s));
    }
}
