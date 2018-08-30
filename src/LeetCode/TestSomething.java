package LeetCode;

import java.util.ArrayDeque;

public class TestSomething {

    private static int calculateSize(int numElements) {
        int initialCapacity = 8;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        return initialCapacity;
    }

    private static void threeNum(int[] nums){
        for (int i = 0; i < 4; i++){
            for (int j = (i+1)%4; j != i; j=(j+1)%4){
                for (int k = (j+1)%4, n = 1; n <= 4; k = (k+1)%4, n++){
                    if (k == i || k == j) continue;
                    System.out.print(i+","+j+","+k);
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
//            ArrayDeque<Integer> deque = new ArrayDeque<>();
//            deque.add(4);
//            deque.addFirst(5);
//            deque.addLast(6);
//            Integer element = deque.element();
//            Integer last = deque.getLast();

//        int length = TestSomething.calculateSize(64);
//        System.out.println(length);

//        int[] nums = {1,2,3,4};
//        TestSomething.threeNum(nums);

//        String s3 = new String("1") + new String("1");
//        String s4 = "11";
//        System.out.println(s3.intern() == s4);

        String s = null;
        String q = null;
        System.out.println(s == q);
    }
}
