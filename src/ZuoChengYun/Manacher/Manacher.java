package ZuoChengYun.Manacher;

/**
 * 返回字符串中最长回文子串的长度
 * Manacher算法
 * 时间复杂度为o(n)
 */
public class Manacher {

    // “abc"变成”#a#b#c#“
    private char[] manacherStr(String s) {
        char[] strArray = s.toCharArray();
        char[] pArray = new char[s.length()*2 + 1];
        int index = 0;
        for (int i = 0; i < pArray.length; i++) {
            if ((i & 1) == 0) {
                pArray[i] = '#';
            }else {
                pArray[i] = strArray[index++];
            }
        }
        return pArray;
    }

    public int solve(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] strArray = manacherStr(s);
        //以i位置上的字符（strArray[i])作为回文中心的情况下，扩出去得到的最大回文半径
        int[] pArray = new int[strArray.length];
        //表示最近一次pR更新时，那个回文中心的位置
        int index = -1;
        //之前遍历的所有字符的所有回文半径中，最右即将到达的位置。
        int pR = -1;
        int max = 0;
        for (int i = 0; i < pArray.length; i++) {
            pArray[i] = i < pR ? Math.min(pArray[index*2 - i], pR-i) : 1;
            while (pArray[i] + i < strArray.length && i - pArray[i] >= 0) {
                if (strArray[pArray[i] + i] == strArray[i-pArray[i]]) {
                    pArray[i]++;
                }else {
                    break;
                }
            }
            if (pArray[i] + i > pR) {
                pR = pArray[i] + i;
                index = i;
            }
            max = Math.max(max, pArray[i]);
        }
        return max-1;
    }

    public static void main(String[] args) {
        Manacher instance = new Manacher();
        String a = "123";
        System.out.println(instance.solve(a));

        String b = "abc1234321ab";
        System.out.println(instance.solve(b));
    }
}
