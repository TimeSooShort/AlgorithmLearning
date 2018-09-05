package LeetCode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode128 {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int num : nums){
            if (map.containsKey(num)) continue;
            Integer leftLn = map.get(num-1);
            Integer rightLn = map.get(num+1);
            int ln = 1;
            if (leftLn != null && rightLn != null) {
                ln = leftLn+rightLn+1;
                map.replace(num-leftLn, ln);
                map.replace(num+rightLn, ln);
                map.put(num, ln);
            }
            else if(leftLn != null) {
                ln = leftLn+1;
                map.replace(num-leftLn, ln);
                map.put(num, ln);
            }
            else if (rightLn != null){
                ln = rightLn+1;
                map.replace(num+rightLn, ln);
                map.put(num, ln);
            }
            else {
                map.put(num, ln);
            }
            max = Math.max(max, ln);
        }
        return max;
    }

    //=========================
    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);
        }
        int max = 0;
        for(int num : nums) {
            if (!set.remove(num)) continue;
            int lt = num-1, rt = num+1;
            while (set.remove(lt)) lt--;
            while (set.remove(rt)) rt++;
            max = Math.max(max, rt-lt-1);
        }
        return max;
    }
}
