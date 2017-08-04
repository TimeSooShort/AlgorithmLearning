package LintCode.String;

public class CountAndSay {

    public String countAndSay(int n) {
        // Write your code here
        StringBuilder older = new StringBuilder();
        older.append(1);
        while (n > 1) {
            n--;
            StringBuilder newer = new StringBuilder();
            int equalsCounts = 0;
            char last = older.charAt(0);
            for (int i = 0; i < older.length(); i++) {
                char cur = older.charAt(i);
                if (cur == last) {
                    equalsCounts++;
                }
                else {
                    newer.append(equalsCounts);
                    newer.append(last);
                    last = cur;
                    equalsCounts = 1;
                }
                if (i == older.length() - 1) {
                    newer.append(equalsCounts);
                    newer.append(cur);
                }
            }
            older = newer;
        }
        return older.toString();
    }
}
