package Other;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *有一批订单记录，数据有订单号，入店时间，离店时间；
 * 输入一个时间值A，需要在这批记录中找到符合入离店时间范围（A大于等于入店时间，
 * 并且A小于等于离店时间）内的所有记录。 单次查询时间复杂度控制在O(logN)
 * ※注意：订单号升序输出
 */
public class FindFromRecord {

    public static List<String> solve(int ln, int time, List<String> records) {
        int lo = 0, hi = ln-1;
        while (lo < hi) {
            int mid = ((hi-lo) >> 1)+ lo;
//            System.out.println("hi:"+records.get(hi));
//            System.out.println("mid:"+records.get(mid));
//            System.out.println("lo:"+records.get(lo));
            int start = Integer.valueOf(records.get(mid).substring(5, 13));
            int end = Integer.valueOf(records.get(mid).substring(14));
            if (end < time) {
                //System.out.println("本次 hi -> mid");
                hi = mid;
            }
            else {
                //System.out.println("本次 lo -> mid");
                lo = mid+1;
            }
        }
//        System.out.println("==================");
//        System.out.println(records.subList(lo, ln));
//        System.out.println("==================");
        if (Integer.valueOf(records.get(lo).substring(5, 13)) > time ||
                Integer.valueOf(records.get(lo).substring(14)) < time) {
            return new ArrayList<>();
        }
        int recordsSt = lo;
        hi = ln-1;
        while (lo < hi) {
            int mid = ((hi-lo) >> 1)+ lo;
            int start = Integer.valueOf(records.get(mid).substring(5, 13));
            if (start > time) hi = mid;
            else lo = mid+1;
        }
        return records.subList(recordsSt, lo);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Temp\\project\\test.txt")));
        int count = Integer.valueOf(reader.readLine().trim());
        int time = Integer.valueOf(reader.readLine().trim());
        ArrayList<String> records = new ArrayList<>();
        String record;
        while ((record = reader.readLine()) != null) {
            records.add(record.trim());
        }

        records.sort((o1, o2) -> {
            int start1 = Integer.valueOf(o1.substring(o1.indexOf(" ")+1, o1.lastIndexOf(" ")));
            int start2 = Integer.valueOf(o2.substring(o2.indexOf(" ")+1, o2.lastIndexOf(" ")));
            return Integer.compare(start1, start2);
        });

        ArrayList<String> clone = (ArrayList<String>) records.clone();

        clone.sort((o1,o2) -> {
            int end1 = Integer.valueOf(o1.substring(o1.lastIndexOf(" ")+1));
            int end2 = Integer.valueOf(o2.substring(o2.lastIndexOf(" ")+1));
            return Integer.compare(end1, end2);
        });

//        for (String s : records) {
//            System.out.println(s);
//        }
//        System.out.println("=========================");

//        // 测试顺序性
//        for (int i = 0; i < count-1; i++) {
//            int start1 = Integer.valueOf(records.get(i).substring(5,13));
//            int start2 = Integer.valueOf(records.get(i+1).substring(5,13));
//            int end1 = Integer.valueOf(records.get(i).substring(14));
//            int end2 = Integer.valueOf(records.get(i+1).substring(14));
//            if (start1 > start2) {
//                System.out.println(false);
//            }else if (start1 == start2 && end1 > end2) {
//                System.out.println(false);
//            }
//        }
//        System.out.println(true);

        List<String> result = solve(count, time, records);
        if(result.isEmpty()) {
            System.out.println("null");
            return;
        }
        result.sort((o1,o2) -> {
            int number1 = Integer.valueOf(o1.substring(0, 4));
            int number2 = Integer.valueOf(o2.substring(0, 4));
            return Integer.compare(number1, number2);
        });
        for(String s : result) {
            System.out.println(s.substring(0,4));
        }
    }
}
