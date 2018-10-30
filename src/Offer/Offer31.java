package Offer;

import java.util.ArrayDeque;

/**
 * 栈的压入，弹出序列. 栈中序列不重复
 */
public class Offer31 {

    // in数组为元素的压入顺序， 判断out数组是否是某种栈的弹出顺序
    public boolean solve(int[] in, int[] out) {
        if (in == null || out == null) throw new IllegalArgumentException("参数不能为空");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int outI = 0, inI = 0;
        while (outI < out.length) {
            if (stack.isEmpty()) {
                for (int i = inI; i < in.length; i++) {
                    if (in[i] == out[outI]) {
                        inI = i+1;
                        outI++;
                        break;
                    }
                    stack.addLast(in[i]);
                }
            }
            else if (stack.peekLast() == out[outI]) {
                outI++;
                stack.pollLast();
            }else if (stack.contains(out[outI])) {
                return false;
            }else {
                for (int i = inI; i < in.length; i++) {
                    if (in[i] == out[outI]) {
                        inI = i+1;
                        outI++;
                        break;
                    }
                    stack.addLast(in[i]);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] in = {1,2,3,4,5};
        int[] out1 = {4,5,3,2,1};
        int[] out2 = {4,3,5,1,2};

        int[] in1 = {3,4,1,5,6,2,8,7,11,15,14,12,17,16};
        int[] out3 = {3,4,6,5,1,8,2,7,14,11,17,12,15,16};

        Offer31 instance = new Offer31();
        System.out.println(instance.solve(in, out1));
        System.out.println(instance.solve(in, out2));
        System.out.println(instance.solve(in1, out3));
    }
}
