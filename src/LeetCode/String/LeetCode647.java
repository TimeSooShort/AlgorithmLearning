package LeetCode.String;

import LeetCode.array.LeetCode64;

public class LeetCode647 {

    private int count;
    public int countSubstrings2(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] arr = s.toCharArray();
        for(int i = 0; i < s.length(); i++) {
            expand(arr, i, i);
            expand(arr, i, i+1);
        }
        return count;
    }

    private void expand(char[] arr, int l, int r) {
        while(l >= 0 && r < arr.length && arr[l] == arr[r]){
            count++;
            l--;
            r++;
        }
    }

    // ===========================================

    private char[] manacherStr(String s) {
        char[] arr = s.toCharArray();
        char[] manacherArr = new char[arr.length*2+1];
        int index = 0;
        for (int i = 0; i < manacherArr.length; i++) {
            manacherArr[i] = (i & 1) == 0 ? '#' : arr[index++];
        }
        return manacherArr;
    }

    // 本想利用manacher算法来实现0（N）时间复杂度，但失败
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] arr = manacherStr(s);
        int[] pArr = new int[arr.length];
        int pr = 0, index=0, count = 0;
        for (int i = 0; i < arr.length; i++) {
            pArr[i] = i < pr ? Math.min(pArr[index*2-i], pr-i) : 1;
            while (pArr[i]+i < arr.length && i-pArr[i] >= 0) {
                if (arr[pArr[i]+i] == arr[i-pArr[i]]) {
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (pArr[i]+i > pr) {
                pr = pArr[i] + i;
                index = i;
            }
//            if ( ((i & 1) == 1 && pArr[i] > 2) || ((i & 1) == 0 && pArr[i] > 1)) count++;
        }
        return count + s.length();
    }

    public static void main(String[] args) {
        LeetCode647 instance = new LeetCode647();
        String s = "aaaaa";
        System.out.println(instance.countSubstrings(s));
    }
}
