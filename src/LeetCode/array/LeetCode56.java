package LeetCode.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LeetCode56 {

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if(intervals == null || intervals.size() == 0) return result;
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start > o2.start) return 1;
                else if(o1.start < o2.start) return -1;
                else return 0;
            }
        });
        System.out.println(intervals.toString());
        result.add(intervals.get(0));
        System.out.println(result.toString());
        for(int i = 1; i < intervals.size(); i++){
            Interval current = intervals.get(i);
            Interval reEnd = result.get(result.size()-1);
            if(current.start <= reEnd.end){
                Interval ob = new Interval(reEnd.start, current.end);
                if(current.end < reEnd.end){
                    ob = new Interval(reEnd.start, reEnd.end);
                }
                result.set(result.size()-1, ob);
            } else {
                result.add(current);
            }
            System.out.println(result.toString());
        }
        return result;
    }

    class Interval{
        Integer start;
        Integer end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval() {
            this.start = 0;
            this.end = 0;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    //--------------------------------------------------------

    public List<Interval> merge2(List<Interval> intervals) {
        if(intervals.size() <= 1) return intervals;
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> result = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for(Interval interval : intervals){
            if(end >= interval.start){
                end = Math.max(end, interval.end);
            }else {
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        result.add(new Interval(start, end));
        return result;
    }

    public static void main(String[] args) {
        LeetCode56 instance = new LeetCode56();
        Interval o1 = instance.new Interval(1,3);
        Interval o2 = instance.new Interval(2,6);
        Interval o3 = instance.new Interval(8,10);
        Interval o4 = instance.new Interval(15,18);
        List<Interval> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        instance.merge(list);
    }
}
