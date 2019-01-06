package LeetCode;

import java.util.*;

public class LeetCode451 {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            map.merge(c, 1, (a, b) -> a+b);
        }
        List[] bucket = new List[s.length()+1];
        for (char c : map.keySet()) {
            if (bucket[map.get(c)] == null) bucket[map.get(c)] = new ArrayList();
            bucket[map.get(c)].add(c);
        }

        for (List list : bucket) {
            if (list != null) System.out.println(list);
        }

        for (int i = bucket.length-1; i > 0; i--) {
            if (bucket[i] != null) {
                for (Object o : bucket[i]) {
                    char c = (char) o;
                    System.out.println("c: " + c);
                    for (int j = 0; j < i; j++) {
                        builder.append(c);
                    }
                }
            }
        }
        return builder.toString();
    }

    public String frequencySort2(String s) {
        char[] sArr = s.toCharArray();
        int[] count = new int[128];
        for (char c : sArr) count[c]++;
        int pos = 0;
        while (pos < sArr.length) {
            int max = 0;
            for (int i = 1; i < 128; i++) {
                if (count[i] > count[max]) max = i;
            }
            for (int i = 0; i < count[max]; i++) {
                sArr[pos++] = (char) max;
            }
            count[max] = 0;
        }
        return new String(sArr);
    }

    public static void main(String[] args) {
        LeetCode451 instance = new LeetCode451();
        System.out.println(instance.frequencySort2("tree"));
    }
}
