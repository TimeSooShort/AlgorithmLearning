package LeetCode.dp;

/**
 * Longest Palindromic Substring:最长回文子串，Manacher算法，O（N)时间复杂度
 */
public class LeetCode5 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherStr(s);
        int[] pArr = new int[str.length];
        int pr = -1;
        int index = -1;
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < pArr.length; i++) {
            pArr[i] = i >= pr ? 1 : Math.min(pArr[index*2-i], pr-i);
            while (i + pArr[i] < pArr.length && i - pArr[i] >= 0) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (i + pArr[i] > pr) {
                index = i;
                pr = i + pArr[i];
            }
            if (pArr[i] > max) {
                max = pArr[i];
                maxIndex = i;
            }
        }
        char[] res = new char[max-1];
        for (int i = maxIndex-max+1, j = 0; i < maxIndex+max; i++) {
            if (str[i] == '#') {
                continue;
            }
            res[j++] = str[i];
        }
        return new String(res);
    }

    private char[] manacherStr(String s) {
        char[] sArr = s.toCharArray();
        char[] res = new char[2*sArr.length + 1];
        for (int i = 0; i < res.length; i++) {
            if ((i & 1) == 0) {
                res[i] = '#';
            }else{
                res[i] = sArr[i/2];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode5 instance = new LeetCode5();
        String s = "babad";
        System.out.println(instance.longestPalindrome(s));
    }
}
