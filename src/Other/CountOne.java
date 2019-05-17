package Other;

/**
 * 计算1~n所有数字中1出现的次数, 0 < n < Integer.MAX_VALUE
 */
public class CountOne {

    public static long countOne(int n) {
        if (n < 10) return 1;
        long[] arr = new long[9]; // arr[0]:1~9 个数为1; arr[1]:1~99 个数为20.。。
        int base = 10; // 与n的位数相同，即 n / base*10 == 0
        arr[0] = 1;
        int i = 0; // i记录n的前一位满时1的个数，如 n 为百位 则 i = 1，arr[1] = 20
        while(n / base >= 10) {
            arr[i+1] = base + arr[i]*10;
            base *= 10;
            i++;
        }
        return count(n, arr, i, base, 0);
    }

    private static long count(int n, long[] arr, int i, int base, long result) {
        int high = n/base;
        if (base == 1) {
            return n == 0 ? result : result+1;
        }
        if (high == 0) {
            result += count(n%base, arr, i-1, base/10, result);
        }
        else if (high == 1) {
            int next = n%base;
            result += next+1 + arr[i] + count(next, arr, i-1, base/10, result);
        }
        else {
            result += base + high*arr[i] + count(n%base, arr, i-1, base/10, result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(countOne(2119436187));
    }
}
