package Other.bilibili2019;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 逆波兰表达式的生成:https://blog.csdn.net/bruce_6/article/details/39205837
 * 逆波兰表达式的计算:https://blog.csdn.net/bruce_6/article/details/39182151
 * 正则表达式表：http://tool.oschina.net/uploads/apidocs/jquery/regexp.html
 */
public class Expression {
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";
    private static final String RIGHT = ")";
    private static final String LEFT = "(";

    // 将中序转为后序
    private static List<String> covertToPost(String inExpress) {
        if (inExpress == null || "".equals(inExpress = inExpress.trim())) throw new RuntimeException("data is empty");
        if (!isNumber(String.valueOf(inExpress.charAt(0)))) throw new RuntimeException("expression must start with number");

        inExpress = replaceAllBlank(inExpress); // 去空
        //System.out.println(inExpress);
        ArrayDeque<String> stack = new ArrayDeque<>();
        List<String> postList = new ArrayList<>();

        int beforeSymIndex = -1; // 上一次与本次之间的数为一个完整的数
        for (int i = 0; i < inExpress.length(); i++) {
            String cur = String.valueOf(inExpress.charAt(i));
            if (isSymbol(cur)) {
                int curLevel = getLevel(cur);
                // 栈为空；cur为"("；cur的level大于栈顶且不为")"；栈顶为'("
                // 这四种情况下直接入栈
                if (stack.isEmpty() || cur.equals(LEFT) ||
                        (getLevel(stack.peek()) < curLevel && !cur.equals(RIGHT)) ||
                        stack.peek().equals(LEFT)) {
                    stack.push(cur);
                }
                // cur为")"，将栈中"("之上的全部出栈，包括"("
                else if (cur.equals(RIGHT)) {
                    String sym;
                    while (!(sym = stack.pop()).equals(LEFT)) {
                        postList.add(sym);
                    }
                }
                // cur的level <= 栈顶，不断出栈直到为空或栈顶level < cur 或 栈顶为"("
                else if (getLevel(stack.peek()) >= curLevel) {
                    while (!stack.isEmpty() && getLevel(stack.peek()) >= curLevel
                            && !stack.peek().equals(LEFT)) {
                        postList.add(stack.pop());
                    }
                    stack.push(cur);
                }
                beforeSymIndex = i; // 记录字符位置
            }
            // 数字可能为多位，所以直到下个位置为字符或越界，beforeSymIndex 到 i之间的为数
            else if (isNumber(cur)){
                if (i == inExpress.length()-1 || isSymbol(String.valueOf(inExpress.charAt(i+1)))) {
                    postList.add(inExpress.substring(beforeSymIndex+1, i+1));
                }
            }
            else {
                throw new RuntimeException("expression not right, char = "+cur);
            }
        }
        // 栈中剩余
        while (!stack.isEmpty()) {
            postList.add(stack.pop());
        }
        return postList;
    }

    // 辅助栈，遍历后序序列，是数组则入栈，遇到字符则弹出两个数组进行计算，结果再入栈
    // 注意先弹出的是被操作数。
    private static String calculate(List<String> post) {
        //System.out.println(post);
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String s : post) {
            if (isNumber(s)) {
                //System.out.println("number: "+s);
                stack.push(s);
            }
            else {
                //System.out.println("sym: "+s);
                //System.out.println(stack.peek());
                BigDecimal right = new BigDecimal(stack.pop());
                //System.out.println(stack.peek());
                BigDecimal left = new BigDecimal(stack.pop());
                BigDecimal result = cal(left, right, s); // 注意先弹出来的是被操作数
                stack.push(result.toString());
            }
        }
        return stack.pop();
    }

    private static BigDecimal cal(BigDecimal a, BigDecimal b, String sym) {
        BigDecimal result = null;
        switch (sym) {
            case ADD : result = a.add(b); break;
            case SUB : result = a.subtract(b); break;
            case MUL : result = a.multiply(b); break;
            case DIV : {
                // 小数点后保留两位，四舍五入
                result = a.divide(b,2, BigDecimal.ROUND_HALF_UP);
                //System.out.println(result);
                break;
            }
        }
        return result;
    }

    private static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("[\\d]+");
        return pattern.matcher(s).matches();
    }

    private static boolean isSymbol(String s) {
        return s.matches("\\+|-|\\*|/|\\(|\\)");
    }

    private static String replaceAllBlank(String s) {
        // \\s+ 匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
        return s.replaceAll("\\s+","");
    }

    private static int getLevel(String level) {
        if ("+".equals(level) || "-".equals(level)) {
            return 1;
        }else if ("*".equals(level) || "/".equals(level)) {
            return 2;
        }else {
            return 3;
        }
    }

    public static void main(String[] args) {
        String inExpress = " 34 + 57- 68+((3+2)*7)-34/ 5+ (( 43-2 )/ 3) ";
        System.out.println(calculate(covertToPost(inExpress)));
    }
}
