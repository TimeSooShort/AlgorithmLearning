package Offer;

import java.util.ArrayList;
import java.util.List;

public class Offer38Extand {

    public static void combination(char[] arr, int start, int num, ArrayList<Character> list) {
        // assert arr != null && arr.length != 0;
        if (num == 0) {
            System.out.print(list + " ");
            return;
        }
        if (start >= arr.length) return;
        list.add(arr[start]);
        combination(arr, start+1, num-1, list);
        list.remove(list.size()-1);
        combination(arr, start+1, num, list);
    }

    public static void combination2(List<List<Character>> result, List<Character> list, char[] arr, int start) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < arr.length; i++) {
            list.add(arr[i]);
            combination2(result, list, arr, i+1);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c'};
//        for (int i = 1; i < 5; i++) {
//            System.out.println("组合大小为" + i);
//            combination(arr, 0, i, new ArrayList<>());
//            System.out.println();
//        }
        List<List<Character>> result = new ArrayList<>();
        combination2(result, new ArrayList<>(), arr, 0);
        System.out.println(result);
    }
}
