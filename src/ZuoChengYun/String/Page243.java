package ZuoChengYun.String;

/**
 * 字符串中数字子串的求和
 */
public class Page243 {

    public int solve(String s) {
        if (s == null) return 0;
        int res = 0;
        int num = 0;
        boolean posi = true;
        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - '0';
            if (cur < 0 || cur > 9) {
                res += num;
                num = 0;
                if (s.charAt(i) == '-') {
                    if (i-1 >= 0 && s.charAt(i-1) == '-') {
                        posi = !posi;
                    }
                    else {
                        posi = true;
                    }
                }
            }
            else {
                num = num * 10 + (posi ? cur : -cur);
            }
        }
        res += num;
        return res;
    }
}
