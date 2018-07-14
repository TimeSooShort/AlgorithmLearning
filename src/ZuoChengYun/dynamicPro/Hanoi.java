package ZuoChengYun.dynamicPro;

/**
 * 汉诺塔问题
 */
public class Hanoi {

    public void solve(int n) {
        if (n > 0) {
            process(n ,"from", "mid", "to");
        }
    }

    private void process(int n, String from, String mid, String to) {
        if (n == 1) {
            System.out.println("move from " + from + " to " + to);
        }else {
            process(n-1, from, to, mid);
            process(1, from, mid, to);
            process(n-1, mid, from, to);
        }
    }
}
