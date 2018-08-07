package LeetCode.String;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class LeetCode17 {

    // 利用queue的先进先出的特点
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return null;
        }

        LinkedList<String> queue = new LinkedList<>();
        String[] table = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        queue.addFirst("");
        while (queue.peek().length() != digits.length()){
            String element = queue.poll();
            String tableNum = table[digits.charAt(element.length())-'0'];
            for (int i = 0; i < tableNum.length(); i++) {
                queue.addLast(element + tableNum.charAt(i));
            }
        }
        return queue;
    }

    // 采用深度优先搜索的方法
    public List<String> letterCombinations2(String digits) {
        if (digits == null || digits.length() == 0) {
            return null;
        }
        String[] table = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> queue = new LinkedList<>();
        dfs(digits, table, queue, 0, "");
        return queue;
    }

    private void dfs(String digits, String[] table, List<String> res, int index, String temp) {
        if (index == digits.length()) {
            res.add(temp);
        }else {
            String numMap = table[digits.charAt(index)-'0'];
            for (int i = 0; i < numMap.length(); i++) {
                dfs(digits, table, res, index+1, temp + numMap.charAt(i));
            }
        }
    }

    public static void main(String[] args) {
        LeetCode17 instance = new LeetCode17();
        String s = "23";
        System.out.println(instance.letterCombinations2(s));
    }
}
