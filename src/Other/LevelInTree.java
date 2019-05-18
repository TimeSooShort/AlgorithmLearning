package Other;

import java.util.*;

/**
 * 给定层级，中序返回叶子节点及前序，后序;
 *
 */
public class LevelInTree {
    private static class Node{
        int val;
        Node left, right;
        Node(int val) {
            this.val = val;
        }
    }

    public static void levelIn(int[] level, int[] in) {
        int[] help = new int[in.length];
        int[] left = new int[in.length];
        int[] right = new int[in.length];
        Map<Integer,Integer> inMap = new HashMap<>(); // val -> index in inArray
        Map<Integer, Node> inIToNode = new HashMap<>(); // index in inArray -> Node(val)
        for (int i = 0; i < in.length; i++) {
            inMap.put(in[i], i);
        }
        for (int le : level) {
            int inI = inMap.get(le);
            help[inI] = le;
            Node cur = new Node(le);
            inIToNode.put(inI, cur);
            int leftClosest = search(help, inI, true);
            if (leftClosest != -1) {
                if (right[leftClosest] == 0) {
                    right[leftClosest] = le;
                    inIToNode.get(leftClosest).right = cur;
                }
                else {
                    int rightClosest = search(help, inI, false);
                    assert rightClosest != -1 && left[rightClosest] == 0;
                    left[rightClosest] = le;
                    inIToNode.get(rightClosest).left = cur;
                }
            }else {
                int rightClosest = search(help, inI, false);
                if (rightClosest != -1) {
                    assert left[rightClosest] == 0;
                    left[rightClosest] = le;
                    inIToNode.get(rightClosest).left = cur;
                }
            }
        }
        Node root = inIToNode.get(inMap.get(level[0]));
        StringBuilder nul = new StringBuilder();
        String pre = preOrderAndNul(root, nul);
        String post = postOrder(root);
        System.out.println(nul.toString().trim());
        System.out.println(pre);
        System.out.println(post);
    }

    private static int search(int[] help, int i, boolean left) {
        if (left) {
            --i;
            for (; i >= 0; i--) {
                if (help[i] != 0) return i;
            }
        }else {
            ++i;
            for (; i < help.length; i++) {
                if (help[i] != 0) return i;
            }
        }
        return -1;
    }

    private static String preOrderAndNul(Node root, StringBuilder nulString) {
        StringBuilder builder = new StringBuilder();
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            builder.append(node.val).append(" ");
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
            if (node.left == null && node.right == null) nulString.append(node.val).append(" ");
        }
        return builder.toString().trim();
    }

    private static String postOrder(Node root) {
        StringBuilder builder = new StringBuilder();
        Deque<Node> stack1 = new ArrayDeque<>();
        Deque<Node> stack2 = new ArrayDeque<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            stack2.push(node);
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }
        while (!stack2.isEmpty()) {
            builder.append(stack2.pop().val).append(" ");
        }
        return builder.toString().trim();
    }

    // 方法二利用递归构建二叉树
    public static Node levelIn2(int[] level, int[] in) {
//        System.out.println(Arrays.toString(level));
//        System.out.println(Arrays.toString(in));
        if (in.length == 0) return null;
        Node cur = new Node(level[0]);
        int index = 0;
        while (in[index] != level[0]) index++;
        int[] leftIn = new int[index];
        int[] rightIn = new int[in.length-index-1];

        System.arraycopy(in, 0, leftIn, 0, leftIn.length);
        for (int i = 0; i < rightIn.length; i++) {
            rightIn[i] = in[index+i+1];
        }

//        System.out.println(Arrays.toString(leftIn));
//        System.out.println(Arrays.toString(rightIn));

        int[] leftLevel = new int[leftIn.length];
        int[] rightLevel = new int[rightIn.length];
        int li = 0, ri = 0;
        for (int i = 1; i < level.length; i++) {
            if (contains(leftIn, level[i])) {
                //System.out.println("left:"+i);
                leftLevel[li++] = level[i];
            }else {
                //System.out.println("right:"+i);
                rightLevel[ri++] = level[i];
            }
        }

//        System.out.println(Arrays.toString(leftLevel));
//        System.out.println(Arrays.toString(rightLevel));

        cur.left = levelIn2(leftLevel, leftIn);
        cur.right = levelIn2(rightLevel, rightIn);
        return cur;
    }

    private static boolean contains(int[] arr, int key) {
        for (int ele : arr) {
            if (ele == key) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] level = {3,5,4,2,6,7,1};
        int[] in = {2,5,3,6,4,7,1};
        //levelIn(level, in);

        int[] level2 = {3,5,4,7,11,9,2,1,12,13,14,17,15,16,18,19};
        int[] in2 = {2,7,18,17,19,1,5,12,11,13,3,9,15,14,16,4};
        levelIn(level2,in2);

        System.out.println("======test levelIn2========");

        Node root = levelIn2(level2, in2);
        StringBuilder nul = new StringBuilder();
        String preOrder = preOrderAndNul(root, nul);
        String postOrder = postOrder(root);
        System.out.println(nul);
        System.out.println(preOrder);
        System.out.println(postOrder);
//        Scanner scanner = new Scanner(System.in);
//        String[] levelString = scanner.nextLine().trim().split(" ");
//        String[] inString = scanner.nextLine().trim().split(" ");
//        int ln = levelString.length;
//        int[] level = new int[ln];
//        int[] in = new int[ln];
//        for (int i = 0; i < ln; i++) {
//            level[i] = Integer.valueOf(levelString[i]);
//            in[i] = Integer.valueOf(inString[i]);
//        }
//        levelIn(level, in);
    }
}
