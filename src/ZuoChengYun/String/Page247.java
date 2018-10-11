package ZuoChengYun.String;

/**
 *  判断两个字符串是否互为旋转词。利用kmp算法，时间复杂度为O(N)
 */
public class Page247 {

    private int[] getNextArr(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] nextArr = new int[match.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int i = 2;
        int k = 0;
        while (i < nextArr.length) {
            if (match[i-1] == match[k]) {
                nextArr[i++] = ++k;
            }
            else if(k > 0) {
                k = nextArr[k];
            }
            else {
                nextArr[i++] = 0;
            }
        }
        return nextArr;
    }

    private int kmp(String s, String match) {
        if (s == null || match == null || match.length() == 0 || s.length() < match.length()) {
            return -1;
        }
        char[] sArr = s.toCharArray();
        char[] mArr = match.toCharArray();
        int[] nextArr = getNextArr(mArr);
        int si = 0;
        int mi = 0;
        while (si < sArr.length && mi < mArr.length) {
            if (sArr[si] == mArr[mi]) {
                si++;
                mi++;
            }
            else if (mi == 0) {
                si++;
            }
            else {
                mi = nextArr[mi];
            }
        }
        return mi == mArr.length ? si-mi : -1;
    }

    public boolean solve(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        String b = s2 + s2;
        return kmp(b, s1) != -1;
    }
}
