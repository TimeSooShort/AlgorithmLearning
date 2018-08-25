package LeetCode;

import java.util.ArrayDeque;

public class LeetCode32 {
    public int longestValidParentheses(String s) {
        if (s == null) return -1;
        if (s.length() == 0) return 0;

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.addLast(i);
            else {
                if (!stack.isEmpty() && s.charAt(stack.peekLast()) == '('){
                    stack.pollLast();
                }else{
                    stack.addLast(i);
                }
            }
        }
        System.out.println(stack);
        int max = 0;
        if (stack.isEmpty()) {
            max = s.length();
        }else{
            int end = s.length();
            while (!stack.isEmpty()){
                max = Math.max(max, end-stack.peekLast()-1);
                end = stack.pollLast();
            }
            max = Math.max(max, end);
        }
        return max;
    }

    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int leftCnt = 0, rightCnt = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCnt++;
            } else {
                rightCnt++;
            }

            if (leftCnt == rightCnt) {
                res = Math.max(res, leftCnt * 2);
            } else if (leftCnt < rightCnt) {
                leftCnt = 0;
                rightCnt = 0;
            }
        }

        leftCnt = 0;
        rightCnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                rightCnt++;
            } else {
                leftCnt++;
            }

            if (leftCnt == rightCnt) {
                res = Math.max(res, leftCnt * 2);
            } else if (rightCnt < leftCnt) {
                leftCnt = 0;
                rightCnt = 0;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LeetCode32 instance = new LeetCode32();
        String s = "((())()()(()()";
        System.out.println(instance.longestValidParentheses(s)); //8

        System.out.println(instance.longestValidParentheses(")()())")); // 4
        System.out.println(instance.longestValidParentheses("(()")); //2
        System.out.println(instance.longestValidParentheses("()")); //2
        System.out.println(instance.longestValidParentheses("()())))()()()")); //6
        System.out.println(instance.longestValidParentheses("())")); //2
    }
}
