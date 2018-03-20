package liuyubobo.heap;

public class Maxheap<Item extends Comparable> {

    protected Item[] datas;
    protected int capacity;
    protected int count;

    public Maxheap(int capacity) {
        this.capacity = capacity;
        datas = (Item[]) new Comparable[capacity+1];
        count = 0;
    }

    public void insert(Item item){
        assert count+1 <= capacity;
        datas[++count] = item;
        shiftUp(count);
    }

    public int size(){
        return count;
    }
    public boolean isEmpty(){
        return count == 0;
    }

    private void shiftUp(int k){
        while (k >1 && datas[k].compareTo(datas[k/2]) > 0){
            swap(k, k/2);
            k /= 2;
        }
    }
    private void swap(int i, int j){
        Item t = datas[i];
        datas[i] = datas[j];
        datas[j] = t;
    }

    public Item getMax(){
        assert count > 0;
        return datas[1];
    }

    public Item extraMax(){
        assert count > 0;
        Item max = datas[1];
        swap(1, count);
        count--;
        shiftDown(1);
        return max;
    }
    private void shiftDown(int k){
        while (k*2 <= count) {
            int j = k * 2;
            if (j+1 <= count && datas[j].compareTo(datas[j+1]) < 0){
                j++;
            }
            if (datas[k].compareTo(datas[j]) >= 0){
                break;
            }
            swap(k,j);
            k = j;
        }
    }

    public static void main(String[] args) {
        Maxheap<Integer> maxheap = new Maxheap<>(100);
        int n = 100;
        int m = 100;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++){
            maxheap.insert((int) (Math.random()*m));
        }
        for (int j = 0; j < n; j++){
            int max = maxheap.extraMax();
            arr[j] = max;
            System.out.print(max + " ");
        }
        System.out.println();
        boolean result = false;
        for (int i = 1; i < n; i++){
            if (arr[i].compareTo(arr[i-1]) > 0){
                result = true;
                System.out.println("failed");
                break;
            }
        }
        if (!result){
            System.out.println("arr is ordered");
        }
    }
}
