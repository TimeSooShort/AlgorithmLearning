package LeetCode.HashTable;

import java.util.Stack;

/**
 * Created by Administrator on 2017/12/13.
 */
public class LeetCode84 {
    static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int max_value = 0;
        int top_area = 0;
        while(i < heights.length) {
            if(stack.empty() || heights[stack.peek()] <= heights[i]) {
                System.out.println("in stack: " + heights[i]);
                stack.push(i++);
            }
            else {
                int top = stack.pop();
                System.out.println("out stack: " + heights[top]);
                top_area = heights[top] * (stack.empty() ? i : i-stack.peek()-1);
                prints(heights[top], stack, i, top_area);
                if(max_value < top_area) {
                    max_value = top_area;
                }
            }
        }
        //这里可以通过将heights长度加1，让heights[n]=0,来省去下面一步
        while(!stack.empty()) {
            int top = stack.pop();
            System.out.println("--------------------------");
            top_area = heights[top] * (stack.empty() ? i : i-stack.peek()-1);
            prints(heights[top], stack, i, top_area);
            if(max_value < top_area) {
                max_value = top_area;
            }
        }
        return max_value;
    }

    private static void prints(int height, Stack<Integer> stack, int i, int top_area) {
        if (stack.empty()) {
            System.out.println("empty stack, i = " + i);
        }
        else {
            System.out.println("i-stack.peek()-1 = " + (i-stack.peek()-1));
        }
        System.out.println("heights[top]=" + height + ";");
        System.out.println(top_area);
    }

    public static void main(String[] args) {
        int[] test = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(test));
    }
}
