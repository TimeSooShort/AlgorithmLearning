package ZuoChengYun.KMP;

public class KMP {

    private int[] getNextArr(char[] match) {
        if (match.length == 1) {
            return new int[] {-1};
        }
        int[] nextArr = new int[match.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int i = 2;
        int k = 0;
        while (i < nextArr.length) {
            if (match[i-1] == match[k]) {
                nextArr[i] = ++k;
                i++;
            }else if (k > 0) {
                k = nextArr[k];
            }else {
                nextArr[i] = 0;
                i++;
            }
        }
        return nextArr;
    }

    public int solve(String s, String match) {
        if (s == null || match == null || match.length() < 1 || s.length() < match.length()) {
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
            }else if (mi == 0) {
                si++;
            }else {
                mi = nextArr[si];
            }
        }
        return mi == mArr.length ? si-mi : -1;
    }

    public static void main(String[] args) {
        KMP instance = new KMP();
        System.out.println(instance.solve("acbc", "bc"));
        System.out.println(instance.solve("acbc", "bcc"));
        System.out.println(instance.solve("aabccd", "aa"));
    }
}
