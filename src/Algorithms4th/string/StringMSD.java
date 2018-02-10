package Algorithms4th.string;


/**
 * Created by Administrator on 2018/2/11.
 */
public class StringMSD {

    private static final int R = 256;
    private static final int CUTOFF = 15;

    public void sort(String[] a){
        int n = a.length;
        String[] aux = new String[n];
        sort(a, aux, 0, n-1, 0);
    }

    private void sort(String[] a, String[] aux, int lo, int hi, int d){
        if (hi <= lo + CUTOFF){
            insertion(a, lo, hi, d);
            return;
        }
        int[] count = new int[R+2];
        int i;
        for (i = lo; i <= hi; ++i){
            int c = charAt(a[i], d);
            count[c+2]++;
        }
        for (i = 0; i < R+1; ++i){
            count[i+1] += count[i];
        }
        for (i = lo; i <= hi; ++i){
            int c = charAt(a[i], d);
            aux[count[c+1]++] = a[i];
        }
        for (i = lo; i <= hi; ++i){
            a[i] = aux[i-lo];
        }
        for (i = 0; i < R; ++i){
            sort(a, aux, lo+count[i], lo+count[i+1]-1, d+1);
        }
    }

    private int charAt(String s, int d){
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    private void insertion(String[] a, int lo, int hi, int d){
        for(int i = lo; i <= hi; ++i){
            for(int j = i; j > lo && less(a[j], a[j-1], d); --j){
                exch(a, j, j-1);
            }
        }
    }

    private boolean less(String a, String b, int d){
        for (int i = d; i < Math.min(a.length(), b.length()); ++i){
            if (a.charAt(i) < b.charAt(i)) return true;
            if (a.charAt(i) > b.charAt(i)) return false;
        }
        return a.length() < b.length();
    }

    private void exch(String[] a, int i, int j){
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
