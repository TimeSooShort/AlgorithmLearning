import java.lang.reflect.Array;
import java.util.Arrays;

public class OddBeforeEven {
	
    public void reOrderArray(int [] array) {
        int even = 0;
        int odd = 0;
        int n = array.length;
        if(array == null || n == 0) {
            return;
        }
        while(even < n) {
            while(even < n &&  (array[even] & 1) != 0) {
                even++;
            }
            odd = even + 1;
            //注意odd<n，这个条件要放在前面，否则ArrayOutOfBounds;
            //因为&&运算第一个判断条件为false，不会在进行第二个条件的判定，避免了数组越界
            while(odd<n && (array[odd] & 1) == 0) {
                odd++;
            }
            if(odd < n) {
                int temp = array[odd];
                for(int k = odd-1; k >= even; k--) {
                    array[k+1] = array[k];
                }
                array[even++] = temp;
            }
            else {
                break;
            }
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array = {1,2,3,4,5,6,7};
		OddBeforeEven test = new OddBeforeEven();
		test.reOrderArray(array);
		System.out.println(Arrays.toString(array));
	}

}
