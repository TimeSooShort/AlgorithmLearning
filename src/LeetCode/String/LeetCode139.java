package LeetCode.String;

import java.util.*;

public class LeetCode139 {

    // dp解法
    public boolean wordBreak(String s, List<String> wordDict){
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // =============backtracking解法
    public boolean wordBreak2(String s, List<String> wordDict) {
        return backtracking(s, wordDict, 0, new boolean[s.length()]);
    }

    private boolean backtracking(String s, List<String> dict, int i, boolean[] unbreakable) {
        if (i == s.length()) return true;
        if (unbreakable[i]) return false;
        for(String string : dict){
            if (!s.startsWith(string, i)) continue;
            if (backtracking(s, dict, i+string.length(), unbreakable)) return true;
        }
        unbreakable[i] = true;
        return false;
    }
    public static void main(String[] args) {
        LeetCode139 instance = new LeetCode139();
        String[] arr = {"apple", "pen"};
        String[] arr2 = {"cats", "dog", "sand", "and", "cat"};
        String[] arr3 = {"a","b","bbb","bbbb"};
        String[] arr4 = {"bc", "cb"};
        String[] arr5 = {"a"};
        String q = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        String[] arr6 = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
        System.out.println(instance.wordBreak(q, Arrays.asList(arr6)));
    }
}
