package LeetCode;

/**
 * 以1为root，则其左子树为null， 右子树个数为n-1,f(1)=f(0)*f(n-1),f(0) = f(1)=1;
 * 则可得出的规律：
 * 1-> f(0)*f(n-1)
 * 2-> f(1)*f(n-2)
 * 3-> f(2)*f(n-3)
 * ....
 * n-1-> f(n-2)*f(1)
 * n-> f(0)*f(0)
 */
public class LeetCode96 {
    public int numTrees(int n) {
        if(n == 0) return 0;
        else {
            int[] store = new int[n+1];
            store[0] = 1;
            store[1] = 1;
            for(int i = 2; i <= n; i++){
                int sum = 0;
                for(int j = 0; j < i/2; j++){
                    sum += store[j] * store[i-j-1];
                }
                sum *= 2;
                if((i & 1) == 1){
                    sum += store[i/2]*store[i/2];
                }
                store[i] = sum;
            }
            return store[n];
        }
    }
}
