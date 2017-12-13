package LeetCode.HashTable;

/**
 * Created by Administrator on 2017/12/13.
 */
public class LeetCode136 {
    public int singleNumber(int[] nums) {
        // int max = Integer.MIN_VALUE;
        // int min = Integer.MAX_VALUE;
        // for(int num: nums) {
        //     if(num < min) min = num;
        //     if(num > max) max = num;
        // }
        // int[] record = new int[max-min+1];
        // for(int num: nums) {
        //     record[num-min]++;
        // }
        // for(int i = 0; i < record.length; i++) {
        //     if(record[i] == 1) return i+min;
        // }
        // return -1;

        int result = 0;
        for(int num: nums) {
            result ^= num;
        }
        return result;
    }
}
