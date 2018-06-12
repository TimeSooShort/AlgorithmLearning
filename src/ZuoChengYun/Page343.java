package ZuoChengYun;

public class Page343 {

    public int solve(int[] arr) {
        int num = 0, time = 0;
        for (int i = 0; i < arr.length; i++) {
            if (time == 0) {
                num = arr[i];
                time = 1;
            }else if (arr[i] == num) {
                time++;
            }else {
                time--;
            }
        }
        time = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) time++;
        }
        if (time > arr.length/2) {
            return num;
        }else {
            System.out.println("没有个数大于一半的数");
        }
        return -1;
    }

    public static void main(String[] args) {
        Page343 instance = new Page343();
        int[] nums = {1,2,3,4,2,3,1,5};
        int[] nums2 = {1,2,3,2,1,2,2,1,2,3,2,6,2,7,2,3,2,2,2,9,2,2,7,5};
        int[] nums3 = {1};
        int[] nums4 = {1,2,3,1};
        int result = instance.solve(nums);
        System.out.println(result);

        result = instance.solve(nums2);
        System.out.println(result);

        result = instance.solve(nums3);
        System.out.println(result);

        result = instance.solve(nums4);
        System.out.println(result);
    }
}
