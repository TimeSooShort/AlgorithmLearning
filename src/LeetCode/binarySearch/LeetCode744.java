package LeetCode.binarySearch;

public class LeetCode744 {

    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, h = letters.length;
        while(l < h) {
            int mid = l+(h-l)/2;
            if(letters[mid] > target) h = mid;
            else l = mid+1;
        }
        return l == letters.length ? letters[0] : letters[l];
    }
}
