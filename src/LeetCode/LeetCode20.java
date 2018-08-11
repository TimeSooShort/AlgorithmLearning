package LeetCode;

import java.util.ArrayDeque;

/**
 * Valid Parentheses
 */
public class LeetCode20 {

    public boolean isValid(String s) {
        if (s == null || (s.length() & 1) == 1) {
            return false;
        }
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char e = s.charAt(i);
            if (e == '(')           stack.push(')');
            else if (e == '[')      stack.push(']');
            else if (e == '{')      stack.push('}');
            else if (stack.isEmpty() || stack.poll() != e) return false;
        }
        return stack.isEmpty();
    }
}
