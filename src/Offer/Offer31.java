package Offer;

import java.util.ArrayDeque;

/**
 * 栈的压入，弹出序列. 栈中序列不重复
 */
public class Offer31 {

    public boolean solve(int[] pushA, int[] popA) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int pushI = 0, popI = 0; pushI < pushA.length; pushI++) {
            stack.addLast(pushA[pushI]);
            while (popI < popA.length && !stack.isEmpty()
                    && stack.peekLast() == popA[popI]) {
                stack.pollLast();
                popI++;
            }
        }
        return stack.isEmpty();
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
