package LeetCode.backtacking;

import java.util.ArrayList;
import java.util.List;

//Palindrome Partitioning
public class LeetCode131 {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        recursive(result, new ArrayList<String>(), s, 0);
        return result;
    }
    private void recursive(List<List<String>> result, List<String> list, String s, int start){
        if(start == s.length()){
            result.add(new ArrayList<>(list));
        } else {
            for(int i = start; i < s.length(); i++){
                if(isPalindrome(s, start, i)){
                    list.add(s.substring(start, i+1));
                    recursive(result, list, s, i+1);
                    list.remove(list.size()-1);
                }
            }
        }

    }
    private boolean isPalindrome(String s, int lo, int hi){
        while(lo < hi){
            if(s.charAt(lo++) != s.charAt(hi--)) return false;
        }
        return true;
    }
}
