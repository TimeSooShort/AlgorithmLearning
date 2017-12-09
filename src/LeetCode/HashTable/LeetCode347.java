package LeetCode.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/9.
 */
public class LeetCode347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length+1];
        Map<Integer, Integer> countNums = new HashMap<>();

        for(int num: nums) {
            countNums.put(num, countNums.getOrDefault(num, 0) + 1);
        }

        for(int key:countNums.keySet()) {
            int count = countNums.get(key);
            if(bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(key);
        }

        List<Integer> result = new ArrayList<>();
        for(int i = bucket.length-1; i > 0 && result.size() < k; i--) {
            if(bucket[i] == null) {
                continue;
            }
            result.addAll(bucket[i]);
        }
        return result;
    }

    //好事更短的解法
    public List<Integer> topKFrequent2(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        List<Integer>[] bucket = new List[nums.length+1];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int num: nums) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        int[] storeCounts = new int[max-min+1];
        for(int num: nums) {
            storeCounts[num-min]++;
        }
        for(int i = 0; i < storeCounts.length; i++) {
            if(storeCounts[i] == 0) continue;
            if(bucket[storeCounts[i]] == null) {
                bucket[storeCounts[i]] = new ArrayList<>();
            }
            bucket[storeCounts[i]].add(i+min);
        }
        for(int j = bucket.length; j > 0 && result.size() < k; j--) {
            if(bucket[j] != null) {
                result.addAll(bucket[j]);
            }
        }
        return result;
    }

}
