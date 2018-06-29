package ZuoChengYun.BFPRT;

import java.util.Arrays;

/**
 * 问题：无序数组的前K个最小的数？
 * 利用BFPRT算法。该算法的精髓就在与其对节分因子的选择上，具体解释在mediumOfArr方法注释
 * 该算法的时间复杂度为O（n）；T(N) = 0(N) + T(N/5) + T(7n/10 + 6);该证明过程在《算法导论》9.3节
 */
public class BFPRT {

    /**
     *  通过getMinKth得到数组中第K小的数，再遍历数组将小于该数的加入结果res若遍历一遍后res未满，
     *  则用kthNum填满。因为如果数组中比kthNum小的数的个数小于k，而数组第k小的数是kthNum，说明
     *  有多个kthNum重复
     * @param arr 无序数组
     * @param k 前K个最小的数
     * @return 结果数组
     */
    public int[] solve(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return null;
        }
        int kthNum = getMinKth(arr, k);
        int[] res = new int[k];
        int i = 0;
        for (int anArr : arr) {
            if (anArr < kthNum) {
                res[i++] = anArr;
            }
        }
        while (i < k) {
            res[i++] = kthNum;
        }
        return res;
    }

    // 拷贝原属组，因为之后要对其进行更改
    private int getMinKth(int[] arr, int k) {
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        return select(copyArr, 0, copyArr.length-1, k-1);
    }

    /**
     * 该方法选出数组中第k小的数。
     * 通过mediumOfArr得到一个数pivotNum，将数组partition，划分为左边小于pivotNum
     * 中间等于，右边大于。比较i（即K-1，因为数组从0开始），若在相等区间里，则直接返回，否则继续递归在
     * 左/右寻找。
     * @param arr 数组
     * @param begin 开始位置
     * @param end 结束位置
     * @param i k-1
     * @return 第k小的数
     */
    private int select(int[] arr, int begin, int end, int i) {
        if (begin == end) {
            return arr[begin];
        }
        int pivotNum = mediumOfArr(arr, begin, end);
        int[] equalPivotIndex = partition(arr, begin, end, pivotNum);
        if (i >= equalPivotIndex[0] && i <= equalPivotIndex[1]) {
            return arr[i];
        }else if (i < equalPivotIndex[0]) {
            return select(arr, begin, equalPivotIndex[0]-1, i);
        }else {
            return select(arr, equalPivotIndex[1]+1, end, i);
        }
    }

    /**
     * 该方法是BFPRT的精髓，对切分因子的选择直接影响切分后的效果。
     * 将数组每5个化为一组，最后剩下的的化为最后一组，求出每组的中位数，
     * 再对由中位数组成的的数组求中位数,调用select得到中位数，即切分因子。
     * 按照上面的找法找到的x能够保证最少有 3n/10 - 6 个数小于x
     * 那么最多有 7n/10 + 6 个数大于x。也就是说该切分因子最少能帮你淘汰掉3n/10 - 6 个数
     * @param arr 数组
     * @param start 开始
     * @param end 结尾
     * @return 按照上面所说的规则找到的数，作为切分因子
     */
    private int mediumOfArr(int[] arr, int start, int end) {
        int ln = end - start + 1;
        int offset = ln % 5 == 0 ? 0 : 1;
        int[] mediumArr = new int[ln/5+offset];
        for (int i = 0; i < mediumArr.length; i++) {
            int begin = i * 5 + start;
            int ends = Math.min(begin+4, end);
            mediumArr[i] = getMedium(arr, begin, ends);
        }
        return select(mediumArr, 0, mediumArr.length-1, mediumArr.length/2);
    }

    /**
     * 返回中位数，要考虑到结尾可能存在个数小于5的分组。
     * 个数等于5的小组sum一定为偶数，则sum/2 + (sum % 2)为该小组第三个数
     * 若末尾最后的小组个数为偶数，则sum一定为奇数，sum/2 + (sum % 2)得到该偶数小组的下中位数
     * @param arr 数组
     * @param start 开始
     * @param end 结束
     * @return 中位数
     */
    private int getMedium(int[] arr, int start, int end) {
        insertion(arr, start, end);
        int sum = start + end;
        int mediumNum = sum/2 + (sum % 2); //取下中位数
        return arr[mediumNum];
    }

    //插排
    private void insertion(int[] arr, int i, int j) {
        for (int k = i+1; k <= j; k++) {
            int val = arr[k];
            int l = k-1;
            for (; l >= 0 && arr[l] > val; l--) {
                arr[l+1] = arr[l];
            }
            arr[l+1] = val;
        }
    }

    /**
     * 按照切分因子pivot将数组分为，小于，等于，大于三部分。返回等于部分的范围
     * @param arr 数组
     * @param start 开始
     * @param end 结尾
     * @param pivot 切分因子
     * @return 返回等于部分的范围
     */
    private int[] partition(int[] arr, int start, int end, int pivot) {
        int cur = start;
        int small = start-1;
        int big = end+1;
        while (cur < big) {
            if (arr[cur] < pivot) {
                swap(arr, ++small, cur++);
            }else if (arr[cur] > pivot) {
                swap(arr, cur, --big);
            }else{
                cur++;
            }
        }
        int[] equalIndex = new int[2];
        equalIndex[0] = small+1;
        equalIndex[1] = big-1;
        return equalIndex;
    }

    //交换
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        BFPRT instance = new BFPRT();
        int[] arr = {2,3,5,8,11,23,6,7,7,11,11,15,24,23,23,17,5,9,13,11,
                11,22,18,2,3,5,8,11,23,6,7,7,11,11,15,24,23,23};
        int[] res = instance.solve(arr, 17);
        System.out.println(Arrays.toString(res));
    }
}
