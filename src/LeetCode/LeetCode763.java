package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode763 {
    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        char[] arr = S.toCharArray();
        int[] ends = new int[26];
        for(int i = 0; i < arr.length; i++) {
            ends[arr[i]-'a'] = i;
        }
        int start = 0, end = 0;
        for(int i = 0; i < arr.length; i++) {
            end = Math.max(end, ends[arr[i]-'a']);
            if(i == end) {
                list.add(i-start+1);
                start = i+1;
            }
        }
        return list;
    }
}
