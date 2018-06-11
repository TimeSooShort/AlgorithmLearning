package ZuoChengYun;

/**
 * 添加最少字符使字符串变为回文结构
 */
public class Page269 {

    public String solve(String s) {
        char[] sArray = s.toCharArray();
        char[] result = new char[sArray.length * 2];

        int start = 0, end = sArray.length-1; //sArray的指针
        int i = 0, j = result.length - 1; //result的指针

        while (start < end) {
            if (sArray[start] == sArray[end]) {
                result[i] = result[j] = sArray[start];
            }
            else {
                result[i] = sArray[start];
                i++;
                result[i] = sArray[end];
                result[j] = sArray[start];
                j--;
                result[j] = sArray[end];
            }
            start++;
            end--;
            i++;
            j--;
        }
        if (start == end) {
            result[i] = sArray[start];
            i++;
        }
        StringBuilder builder = new StringBuilder();
        for (int k = 0; k < i; k++) {
            builder.append(result[k]);
        }
        for (int q = j+1; q < result.length; q++) {
            builder.append(result[q]);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Page269 instance = new Page269();
        String result = instance.solve("ABAC");
        System.out.println(result);

        result = instance.solve("ACBPJFDCA");
        System.out.println(result);

        result = instance.solve("a");
        System.out.println(result);
    }
}
