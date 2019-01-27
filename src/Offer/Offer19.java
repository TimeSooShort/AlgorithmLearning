package Offer;

/**
 * 字符串匹配问题
 */
public class Offer19 {

    public boolean solve(String s, String pattern) {
        if (s == null || pattern == null) return false;
        char[] sArr = s.toCharArray();
        char[] pArr = pattern.toCharArray();
        return isValid(sArr, pArr) && process(sArr, pArr, 0, 0);
    }

    // 函数p(i, j)表示s[si, sln)与p[pi, pln)的匹配情况
    private boolean process(char[] s, char[] p, int si, int pi) {
        if (pi == p.length) return si == s.length;

        if (pi+1 == p.length || p[pi+1] != '*') {
            return si != s.length && (p[pi] == s[si] || p[pi] == '.')
                    && process(s, p, pi+1, si+1);
        }

        while (si != s.length && (p[pi] == s[si] || p[pi] == '.')) {
            if (process(s, p, si, pi+2)) return true;
            si++;
        }
        return process(s, p, si, pi+2);
    }

    // private boolean process(char[] s, char[] p, int si, int pi) {
    //     if(p.length == pi) return si == s.length;
    //     if(pi+1 == p.length || p[pi+1] != '*') {
    //         return si != s.length && (s[si] == p[pi] || p[pi] == '.') && process(s, p, si+1, pi+1);
    //     }
    //     if(si != s.length && (s[si] == p[pi] || p[pi] == '.')){
    //         先令x*匹配一个字符，不成功再令x*一个个去匹配更多，还不成功让x*匹配 ''空
    //         return process(s, p, si+1, pi+2) || process(s, p, si+1, pi) || process(s, p, si, pi+2);
    //     }
    //     return process(s, p, si, pi+2);
    // }

    private boolean isValid(char[] s, char[] p) {
        for (char value : s) {
            if (value == '.' || value == '*') return false;
        }

        for (int i = 0; i < p.length; i++) {
            if (p[i] == '*' && (i == 0 || p[i-1] == '*')) return false;
        }

        return true;
    }

    // =========================================================================

    // 将上述递归改为动态规划，观察上述process函数，发现该函数调用过程中有两个参数是不变的（s, p)
    // 也就是说该函数的状态就是si和ei值的组合。把函数p在所有不同参数（si, pi)的情况下的所有返回值看作一个范围
    // 该范围是一个（sln+1)*(pln+1)的二维数组，并且p(si, pi)在整个递归过程中依赖的总是p(si+1, pi+1)或p(si+k(k>=0), pi+2)
    // 假设dp[i][j]代表p(i, j)的赶回值， 则dp[i][j]只依赖dp[i+1][j+1]或dp[i+k(k>=0)][j+2]。
    public boolean solve2(String s, String pattern) {
        if (s == null || pattern == null) return false;
        char[] sArr = s.toCharArray();
        char[] pArr = pattern.toCharArray();
        // dp[i][j] 表示s[i, sln]与p[j, pln]的匹配情况
        boolean[][] dp = initialDP(sArr, pArr);

        for (int i = sArr.length-1; i >= 0; i--) {
            for (int j = pArr.length-2; j >= 0; j--) {
                if (pArr[j+1] != '*') {
                    dp[i][j] = (sArr[i] == pArr[j] || pArr[j] == '.') && dp[i+1][j+1];
                }else {
                    int si = i;
                    while (si != sArr.length && (sArr[si] == pArr[j] || pArr[j] == '.')) {
                        if (dp[si][j+2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (!dp[i][j]) {
                        dp[i][j] = dp[si][j+2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    private boolean[][] initialDP(char[] sArr, char[] pArr) {
        int sln = sArr.length;
        int pln = pArr.length;
        boolean[][] dp = new boolean[sln+1][pln+1];
        dp[sln][pln] = true;
        for (int i = pln-2; i >= 0; i=i-2) {
            if (pArr[i] != '*' && pArr[i+1] == '*') dp[sln][i] = true;
            else break;
        }
        if (sln>0 && pln>0) {
            if (sArr[sln-1] == pArr[pln-1] || pArr[pln-1] == '.') dp[sln-1][pln-1] = true;
        }
        return dp;
    }
}
