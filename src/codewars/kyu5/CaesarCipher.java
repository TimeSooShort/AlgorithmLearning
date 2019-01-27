package codewars.kyu5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.codewars.com/kata/first-variation-on-caesar-cipher/java
 */
public class CaesarCipher {

    public static List<String> movingShift(String s, int shift) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (!Character.isAlphabetic(arr[i])) continue;
            int num = arr[i] + shift+i; // 移到的位置的大小
            if (Character.isUpperCase(arr[i])) {
                int remainder = (num-'Z') % 26; // 超出的部分，注意0
                arr[i] = (char) (num <= 90 ? num : remainder == 0 ? 'Z' : remainder -1 + 'A');
            }else {
                int remainder = (num-'z') % 26;
                arr[i] = (char) (num <= 122 ? num : remainder == 0 ? 'z': remainder -1 + 'a');
            }
        }
        List<String> result = new ArrayList<>();
        int ln = arr.length;
        int runnerSize = ln%5 == 0 ? ln/5 : ln/5+1; // 平均runner的大小
        int count = ln/runnerSize; // 相同大小runner的个数
        for (int i = 0; i < count; i++) {
            result.add(new String(arr, i*runnerSize, runnerSize));
        }
        int remainder = ln % runnerSize; // 个数小于平均runner的runner的大小
        if (remainder != 0) {
            result.add(new String(arr, ln-remainder, remainder));
        }
        int emptyCount = 5-count-(remainder == 0? 0:1); // 空runner个数
        for (int i = 0; i < emptyCount; i++) {
            result.add("");
        }
        return result;
    }

    public static String  demovingShift(List<String> s, int shift) {
        StringBuilder builder = new StringBuilder();
        for (String ele: s) {
            builder.append(ele);
        }
        char[] arr = builder.toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (!Character.isAlphabetic(arr[i])) continue;
            int num = arr[i] - shift - i; // 当前位置往左移到的位置
            if (Character.isUpperCase(arr[i])) {
                int originToRightLn = ('A'-num)%26; // 原先位置距离最大字母的长度，包括最大字母，注意0
                arr[i] = (char) (num >= 65 ? num : originToRightLn == 0 ? 'A': 'Z'+1-originToRightLn);
            } else {
                int originToRightLn = ('a'-num)%26;
                arr[i] = (char) (num >= 97 ? num : originToRightLn == 0 ? 'a': 'z'+1-originToRightLn);
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        String s = "I should have known that you would have a perfect answer for me!!!";
        List<String> v = Arrays.asList("J vltasl rlhr ", "zdfog odxr ypw", " atasl rlhr p ", "gwkzzyq zntyhv", " lvz wp!!!");
        List<String> result = movingShift(s, 1);
        //System.out.println(result);
        for (int i = 0; i < 5; i++) {
            if (!v.get(i).equals(result.get(i))) {
                System.out.println(false);
            }
        }
        System.out.println("demovingShift");
        String answer = demovingShift(v, 1);
        if (s.equals(demovingShift(v, 1))) {
            System.out.println("demovingShift method success");
        }
    }
}
