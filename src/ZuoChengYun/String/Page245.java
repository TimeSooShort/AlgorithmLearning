package ZuoChengYun.String;

/**
 * 去掉字符串中连续出现K个0的子串
 */
public class Page245 {

    public String solve(String s, int k) {
        char[] arr = s.toCharArray();
        int start = -1, count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                count++;
                start = start == -1 ? i : start;
            }
            else {
                if (count == k) {
                    while(count-- > 0) {
                        arr[start++] = 0;
                    }
                }
                count = 0;
                start = -1;
            }
        }
        if (count == k) {
            while (count-- > 0) {
                arr[start++] = 0;
            }
        }
        return String.valueOf(arr);
    }
}
