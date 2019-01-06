package LeetCode.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;

public class LeetCode345 {

    public String reverse(String s) {
        ArrayList<Character> list = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u','A','E','I','O','U'));
        char[] sArr = s.toCharArray();
        int i = 0, j = sArr.length-1;
        while (i < j) {
            char ic = sArr[i];
            char jc = sArr[j];
            if (!list.contains(ic)) {
                i++;
            }else if (!list.contains(jc)) {
                j--;
            }else {
                sArr[i++] = jc;
                sArr[j--] = ic;
            }
        }
        return new String(sArr);
    }

    // 解法二：优化
    private static boolean[] vowels;
    static{
        vowels = new boolean[256];
        vowels['a'] = true;
        vowels['e'] = true;
        vowels['i'] = true;
        vowels['o'] = true;
        vowels['u'] = true;
        vowels['A'] = true;
        vowels['E'] = true;
        vowels['I'] = true;
        vowels['O'] = true;
        vowels['U'] = true;
    }
    public String reverseVowels(String s) {
        char[] sArr = s.toCharArray();
        int i = 0, j = sArr.length-1;
        while (i < j) {
            if (!vowels[sArr[i]]) {
                i++;
            }else if (!vowels[sArr[j]]) {
                j--;
            }else {
                char temp = sArr[i];
                sArr[i++] = sArr[j];
                sArr[j--] = temp;
            }
        }
        return new String(sArr);
    }
}
