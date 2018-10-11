package ZuoChengYun.String;

/**
 * 在有序但有空的字符串数组中查找字符串的最左位置。
 * 用二分法
 */
public class Page258 {

    public int solve(String[] arr, String s){
        if (arr == null || arr.length == 0 || s == null) return -1;
        int left = 0, right = arr.length-1, mid = 0;
        int res = -1;
        while (left <= right) {
            mid = (left + right)/2;
            if (arr[mid] != null) {
                if (arr[mid].compareTo(s) < 0){
                    left = mid+1;
                }else if (arr[mid].compareTo(s) > 0) {
                    right = mid-1;
                }else {
                    res = mid;
                    right = mid-1;
                }
            }else {
                int i = mid;
                while (arr[i] == null && i >= left) i--;
                if (i < left || arr[i].compareTo(s) < 0) left = mid+1;
                else {
                    res = arr[i].equals(s) ? i : res;
                    right = mid-1;
                }
            }
        }
        return res;
    }
}
