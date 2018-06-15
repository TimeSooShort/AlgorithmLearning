package ZuoChengYun;

/**
 * N!
 */
public class NFactorial {

    private int[] arr = new int[1000];
    private int h = 1; //表示位数

    public void getNFactorial(int n) {
        arr[0] = 1;
        int p = 0; // 表示 /10 的值
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < h; j++) {
                int num = arr[j] * i + p;
                arr[j] = num % 10;
                p = num /10;
            }
            if (p != 0) {
                arr[h] = p;
                h++;
                p = 0;
            }
        }
    }

    public static void main(String[] args) {
        NFactorial instance = new NFactorial();
        instance.getNFactorial(10);
        for (int i = 0; i < instance.h; i++) {
            System.out.print(instance.arr[i] + " ");
        }
    }
}
