package LintCode.String;

public class LongestCommPrefix {

	public String longestCommonPrefix(String[] strs) {
        // write your code here
        if (strs == null || strs.length == 0) {
            return new String();
        }
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char cur = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != cur) {
                    return string.toString();
                }
            }
            string.append(cur);
        }
        return string.toString();
    }
}
