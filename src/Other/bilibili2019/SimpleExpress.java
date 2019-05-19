package Other.bilibili2019;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleExpress {
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "*";

    private static List<String> covertToPost(String express) {
        if (express == null || "".equals(express = express.trim())) return null;
        ArrayDeque<String> stack = new ArrayDeque<>();
        List<String> post = new ArrayList<>();
        int beforeSymIndex = -1;
        for (int i = 0; i < express.length(); i++) {
            String cur = String.valueOf(express.charAt(i));
            if (isSym(cur)) {
                int curLevel  =getLevel(cur);
                if (stack.isEmpty() || curLevel > getLevel(stack.peek())) {
                    stack.push(cur);
                }else {
                    while (!stack.isEmpty() && getLevel(stack.peek()) >= curLevel) {
                        post.add(stack.pop());
                    }
                    stack.push(cur);
                }
                beforeSymIndex = i;
            }else if (i == express.length()-1 || isSym(String.valueOf(express.charAt(i+1)))){
                post.add(express.substring(beforeSymIndex+1, i+1));
            }
        }
        while (!stack.isEmpty()) post.add(stack.pop());
        return post;
    }

    private static String calculate(List<String> post) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String s : post) {
            if (isNum(s)) stack.push(s);
            else {
                BigDecimal right = new BigDecimal(stack.pop());
                BigDecimal left = new BigDecimal(stack.pop());
                BigDecimal result = cal(left, right, s);
                stack.push(result.toString());
            }
        }
        return stack.pop();
    }

    private static boolean isNum(String s) {
        return s.matches("[\\d]+");
    }

    private static boolean isSym(String s) {
        return s.matches("[+\\-*]");
    }

    private static int getLevel(String sym) {
        switch (sym) {
            case ADD :
            case SUB : return 1;
            case MUL : return 2;
            default:return -1;
        }
    }

    private static BigDecimal cal(BigDecimal a, BigDecimal b, String sym) {
        switch (sym) {
            case ADD : return a.add(b);
            case SUB : return a.subtract(b);
            case MUL : return a.multiply(b);
            default: return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String express;
        while (!(express = scanner.nextLine().trim()).equals("END")) {
            System.out.println(calculate(covertToPost(express)));
        }
    }
}
