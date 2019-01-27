package codewars.kyu7;

public class Accumul {

    public static String accum(String s) {
        int capacity = (s.length()+1)*s.length()/2 + s.length()-1;
        char[] result = new char[capacity];
        int curIndex = 0;
        for(int i = 0; i < s.length(); i++){
            char c = Character.toLowerCase(s.charAt(i));
            result[curIndex++] = Character.toUpperCase(c);
            for (int j = 1; j <= i; j++) {
                result[curIndex++] = c;
            }
            if (curIndex < result.length-1) result[curIndex++] = '-';
        }
        return new String(result);
    }

    public static void main(String[] args) {
        String s1 = "Z-Pp-Ggg-Llll-Nnnnn-Rrrrrr-Xxxxxxx-Qqqqqqqq-Eeeeeeeee-Nnnnnnnnnn-Uuuuuuuuuuu";
        System.out.println(accum("ZpglnRxqenU").equals(s1));
    }
}
