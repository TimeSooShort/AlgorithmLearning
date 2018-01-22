package LeetCode.TwoPointer;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/1/22.
 */
public class LeetCode3 {
    public int lengthOfLongestSubstring(String s) {
        //     char[] s_array = s.toCharArray();
        //     int i = 0;
        //     int max = 0;
        //     while(i < s.length()) {
        //         int[] record = new int[256];
        //         record[s_array[i]]++;
        //         for(int j = i+1; j < s.length(); j++) {
        //             if(record[s_array[j]] > 0) {
        //                 max = Math.max(max, j-i);
        //                 i = j;
        //                 break;
        //             }
        //             else {
        //                 record[s_array[j]]++;
        //             }
        //         }
        //     }
        //     return max;
        // }


        //     int max = 0;
        //     HashMap<Character, Integer> map = new HashMap<>();
        //     for(int i = 0,j = 0; i < s.length(); i++) {
        //         if(map.containsKey(s.charAt(i))) {
        //             j = Math.max(j, map.get(s.charAt(i))+1);
        //         }
        //         map.put(s.charAt(i), i);
        //         max = Math.max(max, i-j+1);
        //     }
        //     return max;

        char[] sArray = s.toCharArray();
        int[] store = new int[256];
        Arrays.fill(store, -1);
        int max = 0;
        for(int i = 0, j = 0; i < s.length(); i++) {
            int index = store[sArray[i]];
            if(index >= j) {
                j = index + 1;
            }
            else {
                max = Math.max(max, i-j+1);
            }
            store[sArray[i]] = i;
        }
        return max;
    }
}
