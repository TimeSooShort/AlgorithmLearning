package ZuoChengYun.dynamicPro;

/**
 *  数字字符串转化为字母组合的种数
 */
public class Page225 {

    public int solve(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }

        char[] arr = s.toCharArray();
        return process(arr, 0);
    }

    // 递归函数p(i)的定义是：s[0...i-1]已经转化完成，s[i...N-1]未转化的情况下所能转化组合的种数
    // 如“111123”,p(4)表示“1111”已经转化完，结果什么不重要，s[4,5]即“23”未转化，可能结果有“BC”“W”
    // 两种，即p(4) = 2。
    // 另外规定p(N)等于1，原因看代码。
    // 如果s[i] == '0'，意思是s[0..i-1]已经转换完成，s[i...N-1]以'0'开头是无法转换的
    // 所以p（i）== 0, 最终结果也为0，即无法转化。
    private int process(char[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        if (arr[index] == '0') {
            return 0;
        }

        // char[i,i+1]组成的两位数大于26，则p(i) = p(i+1)；否则p(i) = p(i+1) + p(i+2)
        int res = process(arr, index+1);
        if (index+1 < arr.length && (arr[index] - '0') * 10 + (arr[index+1]-'0') < 27) {
            res += process(arr, index+2);
        }
        return res;
    }

    // 优化
    public int solve2(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] sArr = s.toCharArray();

        // 根据递归分析，利用两个指针从后往前计算，将上面p(i)的概念拿过来
        // 用current变量代表p(i+1)，next代替p(i+2)
        int current = sArr[sArr.length-1] == 0 ? 0 : 1;
        int next = 1;
        for (int i = sArr.length-2; i >= 0; i--) {
            // 不同于上面递归过程中，s[i] = '0'，则p(i)=0；
            // 在for循环中，若sArr[i]='0'，sArr[i++..]的变化是否保留，取决于sArr[i-1,i]组成的两位数
            // 是否小于27，小于则保留。

            // 所以这里要将current的值赋予next
            if (sArr[i] == '0') {
                next = current;
                current = 0;
            }else {
                int temp = current;
                if ((sArr[i]-'0') * 10 + (sArr[i+1]-'0') < 27) {
                    current += next;
                }
                next = temp;
            }
        }
        return current;
    }

    public static void main(String[] args) {
        Page225 instance = new Page225();
        String s = "1111";
        System.out.println(instance.solve(s));
        System.out.println(instance.solve2(s));
    }
}
