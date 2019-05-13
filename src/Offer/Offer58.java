package Offer;

public class Offer58 {

    public static String reverse(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] arr = s.toCharArray();
        revHoleStr(arr);
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                revOneWord(arr,start,i-1);
                start = i+1;
            }
        }
        return new String(arr);
    }

    private static void revHoleStr(char[] arr) {
        int lt = 0, rt = arr.length-1;
        while (lt < rt) {
            char tp = arr[lt];
            arr[lt++] = arr[rt];
            arr[rt--] = tp;
        }
    }

    private static void revOneWord(char[] arr, int start, int end) {
        if (start >= end) return;
        while (start < end) {
            char tmp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = tmp;
        }
    }

    // 第二题：左旋转
    public static String leftReverse(String s, int i) {
        assert s != null && s.length() >= i && i > 0;
        char[] arr = s.toCharArray();
        revOneWord(arr, 0, i-1);
        revOneWord(arr, i, arr.length-1);
        revHoleStr(arr);
        return new String(arr);
    }

    public static void main(String[] args) {
        String s = "I am a man! ";
        String s2 = " I am a man!";
        String s3 = " I am a man ! ";
        System.out.println(reverse(s));
        System.out.println(reverse(s2));
        System.out.println(reverse(s3));

        System.out.println(leftReverse(s, 4));
        System.out.println(leftReverse(s, 1));
        System.out.println(leftReverse(s, 11));
    }
}
