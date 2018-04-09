package liuyubobo.heap;

// 最大索引堆的主要作用不是用于排序,
// 我们在这里使用排序只是为了验证我们的最大索引堆实现的正确性
// 在后续的图论中, 无论是最小生成树算法, 还是最短路径算法, 我们都需要使用索引堆进行优化:)
public class IndexMaxHeap<Item extends Comparable> {

    protected Item[] data;
    protected int[] index; //从1开始存储
    protected int count;
    protected int capacity;

    public IndexMaxHeap(int capacity) {
        this.capacity = capacity;
        data = (Item[]) new Comparable[capacity];
        index = new int[capacity+1];
        count = 0;
    }

    //插入索引为i的value
    public void insert(int i, Item value){
        assert count+1 <= capacity;
        assert i + 1 >= 1 && i + 1 <= capacity;

        index[++count] = i;
        data[i] = value;
        shiftUp(count);
    }

    //实际操作的是index[]索引数组
    private void shiftUp(int k){
        while (k > 1 && data[index[k/2]].compareTo(data[index[k]]) < 0){
            swapIndex(k, k/2);
            k /= 2;
        }
    }

    //交换索引
    private void swapIndex(int i, int j){
        int t = index[i];
        index[i] = index[j];
        index[j] = t;
    }

    //实际操作的是index[]的数组
    private void shiftDown(int k){
        while (k*2 <= count){
            int j = k*2;
            if (j+1 <= count && data[index[j]].compareTo(data[index[j+1]]) < 0){
                j++;
            }
            if (data[index[j]].compareTo(data[index[k]]) <= 0){
                break;
            }
            swapIndex(j, k);
            k = j;
        }
    }

    //取出最大值
    public Item extraMax(){
        assert count > 0;

        Item max = data[index[1]];
        swapIndex(1, count--);
        shiftDown(1);
        return max;
    }

    //取出最大索引值
    public int extraMaxIndex(){
        assert count > 0;

        int reference = index[1];
        swapIndex(1, count--);
        shiftDown(1);
        return reference;
    }

    //最大值
    public Item getMax(){
        assert count > 0;

        return data[index[1]];
    }

    //最大索引值
    public int getMaxIndex(){
        assert count > 0;

        return index[1];
    }

    //通过索引获取值
    public Item getByIndex(int reference){
        assert reference+1 >= 1 && reference+1 <= count;

        return data[reference];
    }

    public void change(int ref, Item newItem){
        if (!isRefExist(ref)){
            throw new IllegalArgumentException("reference to change is not exist");
        }
        data[ref] = newItem;

        for (int i = 1; i <= count; i++){
            if (index[i] == ref){
                shiftUp(i);
                shiftDown(i);
                return;
            }
        }
    }
    private boolean isRefExist(int ref){
        for (int i =1; i <= count; i++){
            if (index[i] == ref){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 10000;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(n);
        for (int i = 0; i <= n-1 ; i++){
            indexMaxHeap.insert(i, (int) (Math.random()*n));
        }
        Integer[] arr = new Integer[n];
        for (int i = n-1; i >= 0; i--){
            arr[i] = indexMaxHeap.extraMax();
        }
        boolean result = false;
        for (int j = 1; j < n; j++){
            if (arr[j].compareTo(arr[j-1]) < 0){
                result = true;
                System.out.println("failed");
                return;
            }
        }
        if (!result){
            System.out.println("success");
        }
    }
}
