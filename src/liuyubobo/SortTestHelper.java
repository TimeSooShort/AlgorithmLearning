package liuyubobo;

import java.lang.reflect.Method;

public class SortTestHelper {

    //产生一个随机数组
    public static Integer[] randomArray(int n ,int rangeL, int rangeR){
        assert  rangeL <= rangeR;

        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Integer((int)(Math.random()*(rangeR-rangeL) + rangeL));
        }
        return array;
    }

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] arr){
        for (int i = 0; i < arr.length-1; i++){
            if (arr[i].compareTo(arr[i+1]) > 0) return false;
        }
        return true;
    }

    //测试sortClassName所对应排序算法排序arr数组的正确性与消耗时间
    public static void testSort(String sortClassName, Comparable[] arr){

        try {
            //包全名
            Class sortClass = Class.forName(sortClassName);
            Method sortMethod = sortClass.getMethod("sort", new Class[]{Comparable[].class});

            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            //method.invoke(Object owner,Object ...args) owner对象中带有参数args的method方法 方法为静态时，owner为null
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();

            assert isSorted(arr);

            System.out.println(sortClass.getSimpleName() + ": " + (endTime-startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
