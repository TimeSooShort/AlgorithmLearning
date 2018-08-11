package LeetCode.backtacking;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22 {

    public List<String> generateParenthesis(int n){
        if (n == 0) return null;
        List<String> res = new ArrayList<>();
        backtack(n, n, res, "");
        return res;
    }

    private void backtack(int open, int close, List<String> res, String temp) {
        if (open == 0 && close == 0) {
            res.add(temp);
            return;
        }
        if (open > 0) {
            backtack(open-1, close, res, temp + "(");
        }
        if (close > open) {
            backtack(open, close-1, res, temp + ")");
        }
    }
}
