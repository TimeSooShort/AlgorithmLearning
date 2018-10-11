package LeetCode;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode621 {

    // 思路错误，一旦最大数所需填充数小于剩下的数目，则最后剩下的数或者不足的空位都可用前面的数来替换或填充
    // 因为最后的字符数是最小的， 用前面的数来替换填充不会造成冲突；注意相同数量的最大数，将它们看作一个数
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0 || n < 0) return 0;
        if (n == 0) return tasks.length;

        int[] count = new int[26];
        for(char c : tasks) {
            count[c-'A']++;
        }
        Arrays.sort(count);
        System.out.println(Arrays.toString(count));
        int start = 0;
        while (count[start] == 0) start++;
        int i = 25;
        int need = 0;
        while (i >= start) {
            int begin = i;
            while (i > start && count[i-1] == count[i]) i--;
            need = (count[begin]-1)*(n - begin + i);
            i--;
            while (i >= start && need > 0) {
                need -= count[i--];
            }
            if (i < start) {
//                if (need > 0) return tasks.length+ need;
//                else if (need < 0) return tasks.length + (-need-1)*(n+1)+1;
//                else return tasks.length;
                if (need > 0) {
                    System.out.println("one, need = " + need);
                    return tasks.length+ need;
                }else if (need < 0) {
                    System.out.println("two, need = " + need);
                    return tasks.length + (-need-1)*(n+1)+1;
                }else {
                    System.out.println("three");
                    return tasks.length;
                }
            }
//            else if (i == start && need == 0){
//                return tasks.length + (count[start]-1)*(n+1)+1-count[start];
//            }

            need = Math.abs(need);
            if (need != 0) {
                int j = i;
                while (j >= start && count[j] > need) {
                    count[j+1] = count[j];
                    j--;
                }
                count[j+1] = need;
                i++;
            }
        }
        return 0;
    }

    // 正确解法。
    public int leastInterval2(char[] tasks, int n) {
        int[] counter = new int[26];
        int max = 0;
        int maxCount = 0;
        for(char task : tasks) {
            counter[task - 'A']++;
            if(max == counter[task - 'A']) {
                maxCount++;
            }
            else if(max < counter[task - 'A']) {
                max = counter[task - 'A'];
                maxCount = 1;
            }
        }

        int partCount = max - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - max * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }
}
