package Other.bilibili2019;

import java.util.Scanner;

public class CmpV1V2 {

    public static int cmp(String v1, String v2) {
        String[] v1Arr = v1.split("\\.");
        String[] v2Arr = v2.split("\\.");
        int min = Math.min(v1Arr.length, v2Arr.length);
        for (int i = 0; i < min; i++) {
            int cmp = Integer.valueOf(v1Arr[i]).compareTo(Integer.valueOf(v2Arr[i]));
            if (cmp != 0) {
                return cmp;
            }
        }
        if (v1Arr.length == v2Arr.length) return 0;
        else if (v1Arr.length == min) return -1;
        else return 1;
    }

    public static void main(String[] args) {
        System.out.println(cmp("2.4.5.12.15.6", "2.4.5.12.16.3.6.7"));
        System.out.println(cmp("50.52.145.192", "185.223.215.112.122"));
//        Scanner scanner = new Scanner(System.in);
//        String[] s = scanner.nextLine().trim().split(" ");
//        System.out.println(cmp(s[0], s[1]));
    }
}
