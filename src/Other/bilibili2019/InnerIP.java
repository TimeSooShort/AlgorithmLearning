package Other.bilibili2019;

import java.util.Scanner;

public class InnerIP {
    public static int innerIp(String s) {
        if (!s.matches("(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))")) return 0;
        int firstDawn = s.indexOf(".");
        int secondDawn = s.indexOf(".",firstDawn+1);
        int one = Integer.valueOf(s.substring(0, firstDawn));
        int two = Integer.valueOf(s.substring(firstDawn+1, secondDawn));
        if (one == 127 || one == 10) return 1;
        else if (one == 192 && two == 168) return 1;
        else if (one == 172 && two <= 31 && two >= 16) return 1;
        else return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(innerIp(scanner.nextLine().trim()));
    }
}
