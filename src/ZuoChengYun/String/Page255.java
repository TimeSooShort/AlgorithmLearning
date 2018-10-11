package ZuoChengYun.String;

import java.util.Arrays;

/**
 * 判断字符串数组中是否所有字符只出现过一次
 */
public class Page255 {

    public boolean solve(char[] s) {
        if (s == null) return false;
        heapSort(s);
        System.out.println(Arrays.toString(s));
        for (int i = 0; i < s.length-1; i++) {
            if (s[i] == s[i+1]) return false;
        }
        return true;
    }

    private void heapSort(char[] s) {
        int n = s.length;
        for (int i = n/2-1; i >= 0; i--) {
            shiftDown(s, i, n);
        }
        for (int i = n-1; i >= 0; i--) {
            swap(s, 0, i);
            shiftDown(s, 0, i);
        }
    }

    private void shiftDown(char[] s, int k, int n) {
        char t = s[k];
        while (k*2+1 < n) {
            int j = k*2+1;
            if (j + 1 < n && s[j] < s[j+1]) j++;
            if (t >= s[j]) break;
            s[k] = s[j];
            k = j;
        }
        s[k] = t;
    }

    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String[] args) {
        Page255 instance = new Page255();
        String s1 = "sdgewtauhvbaoiej";
        String s2 = "gaegr9786234agsg";
        String s3 = "kiujsbwtq9735mn./";
        System.out.println(instance.solve(s1.toCharArray()));
        System.out.println(instance.solve(s2.toCharArray()));
        System.out.println(instance.solve(s3.toCharArray()));
    }
}
