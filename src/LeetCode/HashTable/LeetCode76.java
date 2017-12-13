package LeetCode.HashTable;

/**
 * Created by Administrator on 2017/12/13.
 */
public class LeetCode76 {
    public String minWindow(String s, String t) {
        if(s == null || t == null || s.length() < t.length()) return "";

        char[] s_array = s.toCharArray();
        char[] t_array = t.toCharArray();
        int[] t_count = new int[256];
        for(char t_char: t_array) {
            t_count[t_char]++;
        }
        int start = 0;
        int end = 0;
        int window_length = Integer.MAX_VALUE;
        int window_start = -1;
        int count = t_array.length;
        while(end < s_array.length) {
            if(t_count[s_array[end]] > 0) count--;
            t_count[s_array[end]]--;
            while(count == 0) {
                t_count[s_array[start]]++;
                if(t_count[s_array[start]] > 0) count++;
                if(end-start+1 < window_length) {
                    window_start = start;
                    window_length = end-start+1;
                }
                start++;
            }
            end++;
        }
        if(window_start == -1) return "";
        return s.substring(window_start, window_start+window_length);
    }
}
