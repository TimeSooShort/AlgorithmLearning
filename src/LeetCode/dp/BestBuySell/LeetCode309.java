package LeetCode.dp.BestBuySell;

/**
 *  与122题不同在于多了一个限制条件：今天卖了，明天必须休息，后天开始才允许卖
 */
public class LeetCode309 {

    // 动态规划：buy[i]：代表到i天为止，以buy行为结尾的最大利润数（这个buy行为并不是并不一定在第i天）；
    // sell[i]，rest[i]定义与buy类似

    // buy[i] = max{ buy[i-1], rest[i-1]-price}；
    // sell[i] = max{ sell[i-1], buy[i-1]+price};
    // rest[i] = max{ buy[i-1], rest[i-1], sell[i-1]}

    // 根据定义sell[length-1]的值就是最大利润值，求出后返回就可以了
    // 那么问题是如何得出上述表达式的？

    // 一：
    // 先来分析buy[i]，买之前一定是rest，不能出现[sell,buy]的情况，所以表达式
    // buy[i] = max{ buy[i-1], rest[i-1]-price}；
    // 二：
    // 再来分析sell[i]: 卖之前必须买，所以sell[i] = max{ sell[i-1], buy[i-1]+price};，它不能是
    // max{ sell[i-1], buy[i-1]+price， rest[i-1]+price}，这也包含了[sell, rest, sell]的情况
    // 三：
    // rest[i] = max{ buy[i-1], rest[i-1], sell[i-1]}；rest行为前可能是buy|sell|rest

    // 那么问题来了，如何确定不会出现[buy,rest,buy], [sell,rest,sell]？

    // 分析buy与rest表达式可以得到：buy[i] <= rest[i]，所以[buy,rest,buy]不会出现。
    // 再来分析rest[i]，这个表达式意味着什么？先说结论rest[i]存储的就是到 i-1 天的最大利润值，即sell[i-1]
    // 即rest[i] = sell[i-1]。为什么？
    // 由上面分析buy[i]<=rest[i]，那么rest表达式变为rest[i] = max{rest[i-1], sell[i-1]}，
    // rest[i-1]表示到i-1天以rest结尾的最大利润，sell[i-1]表示到i-1天以sell结尾的最大利润，
    // 而sell是唯一使得利润值增加的行为 所以rest[i-1] <= sell[i-1]，表达式变为rest[i] = sell[i-1]，
    // 它所表达的意思就是rest行为所起到作用就是存储，存储前一个最大利润值。

    // 由上面的分析可以将表达式改为
    // buy[i] = max{ buy[i-1], sell[i-2]-price}；
    // sell[i] = max{ sell[i-1], buy[i-1]+price};
    // 于是我们便可以是使用四个变量来替代数组，该题就变成了时间复杂度O（N)，空间复杂度O（1）

    // 由分析可以看出rest它起到的是存储作用，为什么强调这一点呢？来看看LeetCode122题
    // 该题唯一不同之处在于没有要求卖买中间要rest一天，所以也就没必要引入rest，该题的表达式是
    // buy[i] = max{ buy[i-1], sell[i-1]-price}；
    // sell[i] = max{ sell[i-1], buy[i-1]+price}
    // 看到唯一不同的就是122题buy[i]的sell[i-1]-price与309题buy[i]的sell[i-2]-price
    // 从我对题的分析来看，首先rest行为它即不增加也不降低利润，它只存储前一个最大利润值，也就是说对于buy[i]
    // 的定义只与规则有关，122题规则允许买之前一天是卖，所以它的情况要从sell[i-1]开始，因为动态规划就是对
    // 暴力递归的优化（采取空间换时间的方式），所以要包含所有变化情况才能得到正确结果；309题规则规定
    // 变化情况要从sell[i-2]开始考虑。所以从这个角度想的化，也可直接跳过上面的分析过程，只要你能看穿rest的
    // 真正含义。
    public int maxProfit(int[] prices) {
        // 初始值的选择，为什么sell = 0, pre_sell = 0, buy = Integer.MIN_VALUE ？
        // 先假设数组只有两个数的情况，分析下就可得出这样的初值设定
        int sell = 0, pre_sell = 0, buy = Integer.MIN_VALUE, pre_buy;
        for (int price : prices) {
            pre_buy = buy;
            buy = Math.max(buy, pre_sell-price);
            pre_sell = sell;
            sell = Math.max(sell, pre_buy+price);
        }
        return sell;
    }
}
