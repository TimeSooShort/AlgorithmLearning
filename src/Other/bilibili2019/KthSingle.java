package Other.bilibili2019;

import java.util.*;

public class KthSingle {
    private static String kthSingle(int k , String s) {
        if (k <= 0 || s == null || s.length() == 0) return "Myon~";
        char[] count = new char[128];
        char[] sArr  = s.toCharArray();
        Map<Character, Integer> charToIndex = new HashMap<>();
        for (int i = 0; i < sArr.length; i++) {
            char c = sArr[i];
            count[c]++;
            if (count[c] == 2) charToIndex.remove(c);
            else if (count[c] == 1) charToIndex.put(c, i);
        }
        if (k > charToIndex.size()) return "Myon~";
        Integer[] arr =charToIndex.values().toArray(new Integer[charToIndex.size()]);
        Arrays.sort(arr);
        return "["+String.valueOf(sArr[arr[k-1]])+"]";
    }

    public static void main(String[] args) {
        String s1 = "misakamikotodaisuki";
        String s2 = "!bakabaka~ bakabaka~ 1~2~9!";
        String s3 = "3.1415926535897932384626433832795028841971693993751o" +
                "582097494459211451488946419191919l91919hmmhmmahhhhhhhhhh";
        String s4 = "www.bilibili.com/av170001";
        String s5 = "111";
        System.out.println(kthSingle(2, s1));
        System.out.println(kthSingle(3, s2));
        System.out.println(kthSingle(3, s3));
        System.out.println(kthSingle(7, s4));
        System.out.println(kthSingle(1, s5));

//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String kWithString = scanner.nextLine().trim();
//            int empty = kWithString.indexOf(' ');
//            int k = Integer.valueOf(kWithString.substring(0, empty));
//            String string = kWithString.substring(empty+1);
//            System.out.println(kthSingle(k, string));
//        }
    }
}
