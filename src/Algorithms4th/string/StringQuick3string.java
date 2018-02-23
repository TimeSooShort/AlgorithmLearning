package Algorithms4th.string;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Administrator on 2018/2/22.
 */
public class StringQuick3string {

    private static final int CUTOFF = 15;

    private int charAt(String a, int d){
        if (d == a.length()) return -1;
        return a.charAt(d);
    }

    private void exch(String[] a, int i, int j){
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private boolean less(String a, String b, int d){
        for (int i = d; i < Math.min(a.length(), b.length()); i++){
            if (a.charAt(i) < b.charAt(i)) return true;
            if (a.charAt(i) > b.charAt(i)) return false;
        }
        return a.length() < b.length();
    }

    private void insertion(String[] a, int lo, int hi, int d){
        for(int i = lo; i <= hi; i++){
            for (int j = i; j > lo && less(a[j], a[j-1], d); --j){
                exch(a, j-1, j);
            }
        }
    }

    public void sort(String[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1, 0);
    }

    private void sort(String[] a, int lo, int hi, int d){
        if (hi <= lo + CUTOFF){
            insertion(a, lo, hi, d);
            return;
        }
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt){
            int t = charAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if ( t > v) exch(a, gt--, i);
            else i++;
        }
        sort(a, lo, lt-1, d);
        if (v >= 0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }
}
