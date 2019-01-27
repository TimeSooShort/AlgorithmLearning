package Offer;

import java.util.List;

public class TryAgain {

    public static boolean regixMatch(String s, String pattern) {
        if (s == null || pattern == null) return false;
        char[] sArr = s.toCharArray();
        char[] pArr = pattern.toCharArray();
        return isValid(sArr, pArr) && process(sArr, pArr, 0, 0);
    }

    private static boolean process(char[] sArr, char[] pArr, int si, int pi) {
        if (pi == pArr.length) return si == sArr.length;
        if (pi + 1 == pArr.length || pArr[pi+1] != '*') {
            return si != sArr.length && (sArr[si] == pArr[pi] || pArr[pi] == '.')
                    && process(sArr, pArr, si+1, pi+1);
        }
        while (si < sArr.length && (pArr[pi] == sArr[si] || pArr[pi] == '.')) {
            if (process(sArr, pArr, si, pi+2)) return true;
            si++;
        }
        return process(sArr, pArr, si, pi+2);
    }

    private static boolean isValid(char[] sArr, char[] pArr) {
        for (char s : sArr) {
            if (s == '.' || s == '*') return false;
        }
        for (int i = 0; i < pArr.length; i++) {
            if (pArr[i] == '*' && (i == 0 || pArr[i-1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(regixMatch("", "."));
    }
}
