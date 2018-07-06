package ZuoChengYun.dynamicPro;

/**
 * 与Page191,P191Supplement同一类型问题。
 * 该题求的是找钱的方法总数。
 */
public class Page196 {

    //暴力递归求解。时间复杂度O（aim^N)
    public int solve(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    //如果用process(arr, i, aim)代表arr[i....N-1]这些面值的钱组成aim，返回总的方法数
    private int process(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        }else {
            for (int i = 0; arr[index]*i <= aim; i++) {
                res += process(arr, index+1, aim - arr[index]*i);
            }
        }
        return res;
    }

    //---------------------用记忆搜索的方式对暴力递归优化-----------------------------

    //暴力递归之所以复杂度高，是因为重复计算，因此采用辅助空间map来记录递归计算结果
    //因为在递归过程中受两个变量的影响，所以map为二维数组
    // 时间复杂度为 O（N * aim^2),可以类比动态规划，本质上记忆搜索方法等价于动态规划。
    public int solve2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length][aim+1];
        return process2(arr, 0, aim, map);
    }

    // map[i][j]表示arr[i....N-1]组成j的方法个数，即process2的返回值，
    // 若为0则代表该位置还未递归得到结果；
    // 若为-1则代表递归得到了结果，不过结果为0
    private int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        }else {
            for (int i = 0; arr[index]*i <= aim; i++) {
                int match = map[index + 1][aim - arr[index]*i];
                if (match != 0) {
                    res += match == -1 ? 0 : match;
                }else {
                    res += process2(arr, index+1, aim-arr[index]*i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    //---------------------记忆搜索到动态规划-----------------

    // dp[i][j]表示arr[i....N-1]组成j的方法个数
    // 按照定义来分析dp[i][j]的可能情况：
    // 不使用arr[i],等于 dp[i-1][j]
    // 使用一个， dp[i-1][j-arr[i]]; 使用两个，dp[i-1][j-2*arr[i]]；，，，，
    // 使用k个，dp[i-1][j-k*arr[i]] (k >= 0 && j-k*arr[i] <= aim)
    // dp[i][j] = dp[i-1][j] + ........ + dp[i-1][j-k*arr[i]](k >= 0 && j-k*arr[i] <= aim)

    //时间复杂度为 O（N * aim^2)，因为枚举最坏情况为aim
    public int solve3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        //dp[i][j]表示arr[i....N-1]组成j的方法个数
        int[][] dp = new int[arr.length][aim+1];

        //第一列全为1，组成钱为0的方法个数，很明显1种，表示不用使用任何货币
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        //第一行dp[0][j]，将arr[0]所能表示的j赋予1
        for (int i = 1; i * arr[0] <= aim; i++) {
            dp[0][i*arr[0]] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                for (int k = 0; j-arr[i]*k >= 0 ; k++) {
                    dp[i][j] += dp[i-1][j-arr[i]*k];
                }
            }
        }
        return dp[arr.length-1][aim];
    }

    // -----------------对上面进行空间压缩-------------

    public int solve4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim+1];
        dp[0] = 1;
        for (int i = 1; i*arr[0] <= aim; i++) {
            dp[i*arr[0]] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = aim; j > 0; j--) {
                for (int k = 1; j-k*arr[i] >= 0; k++) {  //for循环枚举
                    dp[j] += dp[j-k*arr[i]];
                }
            }
        }
        return dp[aim];
    }

    //-----------优化动态规划中的枚举-------------
    // 上面得出dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i]]........
    // + dp[i-1][j-k*arr[i]](k >= 0 && j-k*arr[i] <= aim)
    // 而 dp[i-1][j-arr[i]] + ........+ dp[i-1][j-k*arr[i]] = dp[i-1][j-arr[i]] + .....
    // + dp[i-1][j-arr[i] - x * arr[i]] = dp[i][j-arr[i]]
    // 综合得dp[i][j] = dp[i-1][j] + dp[i][j-arr[i]]

    // 优化省略了枚举：时间复杂度为 O（N * aim）
    public int solve5(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim+1];

        //第一列全为1，组成钱为0的方法个数，很明显1种，表示不用使用任何货币
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        //第一行dp[0][j]，将arr[0]所能表示的j赋予1
        for (int i = 1; i * arr[0] <= aim; i++) {
            dp[0][i*arr[0]] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] += dp[i][j-arr[i]];
                }
            }
        }
        return dp[arr.length-1][aim];
    }

    //--------------空间压缩--------------
    public int solve6(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim+1];
        dp[0] = 1;
        for (int i = 1; i*arr[0] <= aim; i++) {
            dp[i*arr[0]] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                if (j - arr[i] >= 0) {
                    dp[j] += dp[j-arr[i]];
                }
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[] arr = {5,10,25,1};
        Page196 instance = new Page196();
        System.out.println(instance.solve6(arr, 15)); //6
    }

}
