
public class RotateArray {

	public int minNumberInRotateArraay(int[] array) {
		int lo = 0;
		int hi = array.length - 1;
		int mid  = 0;
		while(lo < hi) {
			if (lo == hi-1) {
				break;
			}
			if (array[lo] < array[hi]) {
				return array[lo];
			}
			mid = (lo + hi)/2;
			if(array[lo] < array[mid]) {
				lo = mid;
				continue;
			}
			if(array[mid] < array[hi]) {
				hi = mid;
				continue;
			}
			while(lo < mid) {
				if (array[lo] == array[mid]) {
					lo++;
				} else if (array[lo] < array[mid]) {
					return array[lo];
				} else {
					hi = mid;
					break;
				}
				
			}
		}
		return Math.min(array[lo], array[hi]);
	}
}
