package ZuoChengYun.KMP;

/**
 * 两个字符串，str与match，长度分别为N，M。N >= M。若str包含match，则返回match在str中开始位置。
 * 利用KMP算法，时间复杂读为O(N)
 */
public class KMP {

    /**
     * 得到match的nextArr数组，nextArr[i]表示match[0 ~ i-1]中前缀（不包含i-1）与后缀（不包含0）最大相等个数
     * @param match
     * @return
     */
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
                mi = nextArr[mi];
            }
        }
        return mi == mArr.length ? si-mi : -1;
    }

    public static void main(String[] args) {
        KMP instance = new KMP();
        System.out.println(instance.solve("acbcfajlgheowiahgog", "jlg"));
        System.out.println(instance.solve("acasdfasgfasgbc", "fas"));
        System.out.println(instance.solve("aabagahhgahbzzxbccd", "hbz"));
    }
}
