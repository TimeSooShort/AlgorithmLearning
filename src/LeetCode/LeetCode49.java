package LeetCode;

import java.util.*;
import java.util.function.Function;

public class LeetCode49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            String k = hash(s);
            map.computeIfAbsent(k, s1 -> new ArrayList<>()).add(s);
//            List<String> value = map.get(k);
//            if(value == null) map.put(k, new ArrayList<>());
//            value.add(s);
        }
        return new ArrayList<>(map.values());
    }

    private String hash(String s){
        int[] num = new int[26];
        for(char c : s.toCharArray()){
            num[c-'a']++;
        }
        return Arrays.toString(num);
    }
}
