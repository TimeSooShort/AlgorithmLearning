package ZuoChengYun.Manacher;

/**
 * 字符串s只能在末尾添加元素，求最少添加元素使得整个字符串为回文结构。
 * 包含末尾元素在内的最大回文子串不应重复，利用Manacher算法
 * 做些改动，当pR == strArray.length，记录此时的pArray[i]值，
 * 则包含末尾值的最大回文子串大小等于pArray[i]-1
 * 所以只要在末尾添加 s 的前 s.length()-pArray[i]+1 的倒序到末尾即可
 */
public class Manacher2 {

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

    public String solve(String s)  {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] strArr = manacherStr(s);
        int[] pArray = new int[strArr.length];
        int pR = -1;
        int index = -1;
        int maxContainsEnd = 0;

        for (int i = 0; i < pArray.length; i++) {
            pArray[i] = i < pR ? Math.min(pArray[index*2-i], pR-i) : 1;
            while (pArray[i]+i < strArr.length && i-pArray[i] >= 0) {
                if (strArr[pArray[i] + i] == strArr[i-pArray[i]]) {
                    pArray[i]++;
                }else {
                    break;
                }
            }
            if (pArray[i] + i > pR) {
                pR = pArray[i] + i;
                index = i;
            }
            if (pArray[i] + i == strArr.length) {
                maxContainsEnd = pArray[i];
                break;
            }
        }
        char[] res = new char[s.length()-maxContainsEnd+1];
        for (int i = 0; i < res.length; i++) {
            res[res.length-1-i] = strArr[i*2+1];
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        Manacher2 instance = new Manacher2();
        String a = "abcd123321";
        System.out.println(instance.solve(a));

        String b = "123";
        System.out.println(instance.solve(b));
    }
}
