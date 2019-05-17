package Other;

import java.util.Scanner;

/**
 * 压缩字符串,
 */
public class Compress {

    public static String compress(String s) {
        if (s == null || s.length() < 2) return s;
        char[] arr = s.toCharArray();
        StringBuilder result = new StringBuilder();
        char c = arr[0];
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == c) {
                count++;
            }else {
                if (count != 0) {
                    result.append(count);
                    count = 0;
                }
                result.append(c);
                c = arr[i];
            }
        }
        if (count != 0) result.append(count);
        result.append(c);
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "xxxyyyyzakkr";
        System.out.println(compress(s));
    }
}
