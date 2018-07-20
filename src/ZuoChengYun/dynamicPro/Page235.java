package ZuoChengYun.dynamicPro;

/**
 * 跳跃游戏
 */
public class Page235 {

    public int solve(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("参数不正确");
        }

        int last = 0;
        int step = 1;
        while (last < arr.length-1) {
            int bound = last + arr[last];
            int nextBound = bound;
            for (int i = last+1; i <= bound; i++) {
                if (i + arr[i] >= arr.length) {
                    nextBound = i + arr[i];
                    break;
                }
                if (i + arr[i] > nextBound) {
                    nextBound = i + arr[i];
                }
            }
            last = nextBound;
            step++;
        }
        if (last >= arr.length) step++;
        return step;
    }

    // 代码繁杂是因为逻辑没整理好，对上面进行优化改造

    public int solve2(int[] arr) {
        int jump = 0,cur = 0, next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                jump++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return jump;
    }

    public static void main(String[] args) {
        Page235 instance = new Page235();
        int[] arr = {3,2,3,1,1,4,2,3,1,3,1,1};
        System.out.println(instance.solve(arr));
        System.out.println(instance.solve2(arr));

        int[] arr2 = {3,2,3,1,1,4};
        System.out.println(instance.solve(arr2));
        System.out.println(instance.solve2(arr2));
    }
}
