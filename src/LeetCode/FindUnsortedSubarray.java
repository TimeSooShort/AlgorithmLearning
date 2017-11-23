package LeetCode;

import java.util.Arrays;

public class FindUnsortedSubarray {
	private int[] nums;
	private int[] toCompare;
	private int length;

	public FindUnsortedSubarray(int[] nums) {
		this.nums = nums;
		this.length = nums.length;
		toCompare = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			toCompare[i] = nums[i];
		}
	}
	
    public int findUnsortedSubarray() {
        quickSort(toCompare, 0, length-1);
        int before = 0, after = length-1;
        for (; before < length; before++) {
            if (toCompare[before] != nums[before]) break;
        }
        if (before != length) {
            for (; after >= 0; after--) {
                if (toCompare[after] != nums[after]) break;
            }
        }
        return after - before + 1;
    }
    
    private int partion(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
//        System.out.println(i + "; " + j);
        int v = a[lo];
        while (true) {
            while (a[++i] < v) {
                if (i == hi) break;
            }
            while (a[--j] > v) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    
    private void quickSort(int[] a, int lo, int hi) {
    	if (lo >= hi) return;
        int j = partion(a, lo, hi);
//        System.out.println("partion: " + j);
        if (j > 0) {
        	quickSort(a, lo, j-1);
        }
        if (j < a.length-1) {
        	quickSort(a, j+1, hi);
        }
    }
    
    private void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
    }

	public static void main(String[] args) {
		int[] nums = {2, 6, 4, 8, 10, 9, 15};
		FindUnsortedSubarray sub = new FindUnsortedSubarray(nums);
		int answer = sub.findUnsortedSubarray();
		System.out.println(Arrays.toString(sub.toCompare));
		System.out.println(answer);
	}

}
