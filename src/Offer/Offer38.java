package Offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串的全排列
 */
public class Offer38 {

    // 方法一：
    public List<List<Character>> permute(String s) {
        if (s == null || s.length() == 0) return null;
        List<List<Character>> result = new ArrayList<>();
        recon(result, new ArrayList<>(), s.toCharArray());
        return result;
    }

    private void recon(List<List<Character>> result, List<Character> list, char[] chas) {
        if (list.size() == chas.length) result.add(list);
        else {
            for (int i = 0; i < chas.length; i++) {
                if (list.contains(chas[i])) continue;
                list.add(chas[i]);
                recon(result, list, chas);
                list.remove(list.size()-1);
            }
        }
    }

    //方法二
    public List<List<Character>> recon2(String s) {
        if (s == null || s.length() == 0) return null;
        List<List<Character>> result = new ArrayList<>();
        recon2(result, s.toCharArray(), 0);
        return result;
    }

    private void recon2(List<List<Character>> result, char[] chas, int start) {
        if (start == chas.length-1) result.add(asList(chas));
        else {
            for (int i = start; i < chas.length; i++) {
                swap(chas, start, i);
                recon2(result, chas, start+1);
                swap(chas,start, i);
            }
        }
    }

    private List<Character> asList(char[] chas) {
        List<Character> list = new ArrayList<>();
        for (Character c : chas) {
            list.add(c);
        }
        return list;
    }

    private void swap(char[] chas, int i, int j) {
        char temp = chas[i];
        chas[i] = chas[j];
        chas[j] = temp;
    }
}
