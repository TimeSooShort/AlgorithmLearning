package ZuoChengYun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 给定一个整形数组和K，打印所有大于N/k的数字
 * 整体解题思路是沿着上一题Page343：整形数组arr，打印出现次数大于一半（N/2）的数
 * 在上一题中使用一个num与time来记录数字及其出现的次数，time==0，则删除num中的值；就是相互抵消
 * 大于一半的数一定会最后留下来；
 *
 * 这一题是上一题的扩展，大于N/k的数，就用k-1个num与time来记录
 *
 * 为什么map剩下的数字里一定有大于N/k的数？因为只有当map.size==k-1的情况下才会减1，那么这一来就变成
 * 最少k个数才会减1，那么你N个数，最多减N/K，所以大于N/k的数一定会剩下来
 */
public class Page343Advance {

    public void solve(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 首先找到k-1个不同的数，并记录它们出现的次数，继续向后遍历
        // 若下一个数map里有则将该数个数即value+1，否则 if (map里个数==k-1)则全部个数-1
        // else 将该数加入map
        for (int anArr : arr) {
            Integer value;
            if ((value = map.get(anArr)) != null) {
                map.replace(anArr, value + 1);
            } else {
                if (map.size() == k - 1) {
                    subtractAll(map, k);
                } else {
                    map.put(anArr, 1);
                }
            }
        }

        map.keySet().forEach(integer -> map.replace(integer, 0));// 将map里所有数的个数即value清零

        // 再次循环统计map里数字的真正个数
        for(int num : arr) {
            Integer val;
            if ((val = map.get(num)) != null) {
                map.replace(num, val+1);
            }
        }
//        boolean print = false;
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if (entry.getValue() > arr.length/k) {
//                System.out.print(entry.getKey() + " ");
//                print = true;
//            }
//        }
        map.forEach((key, value) -> {
            if (value > arr.length / k) {
                System.out.print(key + " ");
            }
        });
//        if (!print) {
//            System.out.println("没有符合条件的");
//        }
    }

    // 将map里全部数字的value值减1，减1后若value==0，则删除该键
    private void subtractAll(Map<Integer, Integer> map, int k) {
        List<Integer> list = new ArrayList<>();
        map.forEach((integer, integer2) -> {
            if (integer2 == 1) {
                list.add(integer);
            }else {
                map.replace(integer, integer2-1);
            }
        });
        if (list.size() != 0) {
            for (Integer key : list) {
                map.remove(key);
            }
        }
    }

    public static void main(String[] args) {
        Page343Advance instance = new Page343Advance();
        int[] arr = {1,2,3,2,2,1,1,2,3,3,3,1,5,6,7,6,5,5,6,7,3,2,1,8,9,11,24,44};
        int[] arr1 = {1,2,3,4,5,2,2,1};
        int[] arr2 = {2};
        instance.solve(arr, 8);
        System.out.println();

        instance.solve(arr1, 4);
        System.out.println();

        instance.solve(arr2, 1);
    }
}
