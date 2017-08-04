package LintCode.String;

public class AddBinary {

    public String addBinary(String a, String b) {
        // Write your code here
        int lastIndexA = a.length() - 1;
        int lastIndexB = b.length() - 1;
        int minLength = Math.min(lastIndexA, lastIndexB);
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while (minLength >= 0) {
            minLength--;
            int lastCharA = a.charAt(lastIndexA--) - '0';
            int lastCharB = b.charAt(lastIndexB--) - '0';
            int sum = lastCharA + lastCharB + carry;
            carry = sum / 2;
            builder.append(sum % 2);
        }
        while (lastIndexA >= 0) {
            int lastRemaingCharA = a.charAt(lastIndexA--) - '0';
            int sum = lastRemaingCharA + carry;
            carry = sum / 2;
            builder.append(sum % 2);
        }
        while (lastIndexB >= 0) {
            int lastRemainingB = b.charAt(lastIndexB--) - '0';
            int sum = lastRemainingB + carry;
            carry = sum / 2;
            builder.append(sum % 2);
        }
        if (carry == 1) {
            builder.append(carry);
        }
        return builder.reverse().toString();
    }
}
