package LintCode.String;

public class LengthOfLastWord {

	public int lengthOfLastWord(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.trim();
        int length = s.length();
        int lastSpa = s.lastIndexOf(" ");
        return length - 1 - lastSpa;
    }
}
