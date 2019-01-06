package LeetCode.TwoPointer;

public class LeetCode680 {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        int i = 0, j = s.length()-1;
        char[] sArr = s.toCharArray();
        while (i < j) {
            if (sArr[i] == sArr[j]) {
                i++;
                j--;
            } else {
                return isPalindromRange(sArr, i, j-1) ||
                        isPalindromRange(sArr, i+1, j);
            }
        }
        return true;
    }

    private boolean isPalindromRange(char[] sArr, int i, int j) {
        for(int k = i; k <= (j-i)/2+i; k++) {
            if(sArr[k] != sArr[j+i-k]) return false;
        }
        return true;
    }
}
