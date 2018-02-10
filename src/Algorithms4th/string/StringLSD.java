package Algorithms4th.string;


/**
 * Created by Administrator on 2018/2/6.
 */
public class StringLSD {

    private static final int BITS_PRE_BITE = 8;

    public void sort(String[] a, int w){
        int n = a.length;
        String[] aux = new String[n];
        int R = 256;
        for (int i = w-1; i >= 0; --i){
            int[] count = new int[R+1];
            int j;
            for (j = 0; j < n; ++j){
                ++count[a[j].charAt(i)+1];
            }
            for (j = 0; j < R; ++j){
                count[j+1] += count[j];
            }
            for (j = 0; j < n; ++j){
                aux[count[a[j].charAt(i)]++] = a[j];
            }
            for (j = 0; j < n; ++j){
                a[j] = aux[j];
            }
        }
    }

    public void sort(int[] a){
        final int BITS = 32;
        final int R = 1 << BITS_PRE_BITE;
        final int w = BITS / BITS_PRE_BITE;
        final int MASK = R-1;

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; ++d){
            int[] count = new int[R+1];
            int i;
            for (i = 0; i < n; ++i){
                int c = ( a[i] >> BITS_PRE_BITE * d) & MASK;
                count[c + 1]++;
            }
            for (i = 0; i < R; ++i){
                count[i+1] += count[i];
            }
            if (d == w-1){
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                int j;
                for(j = 0; j < R/2; ++j){
                    count[j] += shift1;
                }
                for (j = R/2; j < R; j++){
                    count[j] -= shift2;
                }
            }

            for (i = 0; i < n; ++i){
                int c = (a[i] >> BITS_PRE_BITE * d) & MASK;
                aux[count[c]++] = a[i];
            }
            for (i = 0; i < n; ++i) {
                a[i] = aux[i];
            }
        }
    }
}
