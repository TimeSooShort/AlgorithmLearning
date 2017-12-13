package LeetCode.HashTable;

import java.util.Stack;

/**
 * Created by Administrator on 2017/12/13.
 */
public class LeetCode85 {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] height = new int[matrix[0].length];
        for(int i = 0; i < matrix[0].length; i++) {
            if(matrix[0][i] == '1') height[i] = 1;
        }
        int result = lagestRectangleArea(height); //only one row
        result = lagestInLine(matrix, height, result);
        return result;
    }

    private int lagestInLine(char[][] matrix, int[] height, int result) {
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '0') {
                    height[j] = 0;
                }
                else {
                    height[j] += 1;
                }
            }
            result = Math.max(result, lagestRectangleArea(height));
        }
        return result;
    }

    private int lagestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int len = height.length;
        int i = 0;
        int topArea = 0;
        int maxArea = 0;
        while(i < len) {
            if(stack.empty() || height[i] >= height[stack.peek()]) {
                stack.push(i++);
            }
            else {
                int top = stack.pop();
                topArea = height[top] * (stack.empty() ? i : i-1-stack.peek());
                if(maxArea < topArea) {
                    maxArea = topArea;
                }
            }
        }
        while(!stack.empty()) {
            int top = stack.pop();
            topArea = height[top] * (stack.empty() ? i : i-1-stack.peek());
            if(maxArea < topArea) {
                maxArea = topArea;
            }
        }
        return maxArea;
    }
    //方法2
//     public int maximalRectangle(char[][] matrix) {
//         if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
//         int rLen = matrix.length;
//         int cLen = matrix[0].length;
//         int[] height = new int[cLen+1];
//         height[cLen] = 0;
//         int max = 0;

//         for(int i = 0; i < rLen; i++) {
//             Stack<Integer> stack = new Stack<>();
//             for(int j = 0; j <= cLen; j++) {
//                 if(j < cLen) {
//                     height[j] = matrix[i][j] == '1' ? height[j]+1 : 0;
//                 }
//                 if(stack.empty() || height[j] >= height[stack.peek()]) {
//                     stack.push(j);
//                 }
//                 else {
//                     while(!stack.empty() && height[j] < height[stack.peek()]) {
//                         int top = stack.pop();
//                         int top_area = height[top] * (stack.empty() ? j : j-1-stack.peek());
//                         max = Math.max(max, top_area);
//                     }
//                     stack.push(j);
//                 }
//             }
//         }
//         return max;
//     }
}
