package Other.bilibili2019;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Expression {
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";
    private static final String RIGHT = ")";
    private static final String LEFT = "(";

    private static List<String> covertToPost(String inExpress) {
        if (inExpress == null || "".equals(inExpress.trim())) throw new RuntimeException("data is empty");
        if (!isNumber(String.valueOf(inExpress.charAt(0)))) throw new RuntimeException("expression must start with number");

        inExpress = replaceAllBlank(inExpress);
        ArrayDeque<String> stack = new ArrayDeque<>();
        List<String> postList = new ArrayList<>();

        int beforeSymIndex = -1;
        for (int i = 0; i < inExpress.length(); i++) {
            String cur = String.valueOf(inExpress.charAt(i));
            if (isSymbol(cur)) {
                int curLevel = getLevel(cur);
                if (stack.isEmpty() || cur.equals(LEFT) ||
                        (getLevel(stack.peek()) < curLevel && !cur.equals(RIGHT)) ||
                        stack.peek().equals(LEFT)) {
                    stack.push(cur);
                }
                else if (cur.equals(RIGHT)) {
                    String sym;
                    while (!(sym = stack.pop()).equals(LEFT)) {
                        postList.add(sym);
                    }
                }
                else if (getLevel(stack.peek()) >= curLevel) {
                    while (!stack.isEmpty() && getLevel(stack.peek()) >= curLevel
                            && !stack.peek().equals(LEFT)) {
                        postList.add(stack.pop());
                    }
                    stack.push(cur);
                }
                beforeSymIndex = i;
            }else if (isNumber(cur)){
                if (i == inExpress.length()-1 || isSymbol(String.valueOf(inExpress.charAt(i+1)))) {
                    postList.add(inExpress.substring(beforeSymIndex+1, i+1));
                }
            } else {
                throw new RuntimeException("expression not right");
            }
        }
        // 栈中剩余
        while (!stack.isEmpty()) {
            postList.add(stack.pop());
        }
        return postList;
    }

    private static long calculate(List<String> post) {

    }

    private static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("^[-+]?[.\\d]*$");
        return pattern.matcher(s).matches();
    }

    private static boolean isSymbol(String s) {
        return s.matches("\\+|-|\\*|/|\\(|\\)");
    }

    private static String replaceAllBlank(String s) {
        // \\s+ 匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
        return s.replaceAll("\\\\s+","");
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
}
