package LeetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class LeetCode406 {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) return new int[0][0];
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1]-b[1] : b[0]-a[0]);
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] ele : people) {
            list.add(ele[1], ele);
        }
        return list.toArray(new int[people.length][]);
    }
}
