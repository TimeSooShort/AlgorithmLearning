package LeetCode;

import java.util.Arrays;

public class LRUCache {

    private int[] keys;
    private int[] values;
    private int[] useSort;
    private int n;
    private int capacity;
    
    public LRUCache(int capacity) {
        keys = new int[capacity];
        values = new int[capacity];
        useSort = new int[capacity];
        n = 0;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        for (int i = 0; i < n; i++) {
            if (keys[i] == key) {
                changeUseSort(i);
                return values[i];
            }
        }
        return -1;
    }
    
    public void put(int key, int value) {
        int index = contains(key);
        if (index == -1) {
            if (n == capacity) {
                int emptyIndex = useSort[0];
                keys[emptyIndex] = key;
                values[emptyIndex] = value;
                deleteLRU();
                return;
            }
            keys[n] = key;
            values[n] = value;
            useSort[n] = n;
            n++;
        } else {
            values[index] = value;
            changeUseSort(index);
        }
    }
    
    private int contains(int key) {
        for ( int i = 0; i < n; i++) {
            if (keys[i] == key) {
                return i;
            }
        }
        return -1;
    }
    
    private void changeUseSort(int index) {
        int changePoint = 0;
        for (int i = 0; i < n; i++) {
            if (useSort[i] == index) {
                changePoint = i;
                break;
            }
        }
        for (int j = changePoint; j < n-1; j++) {
            useSort[j] = useSort[j+1];
        }
        useSort[n-1] = index;
    }
    
    private void deleteLRU() {
        int deleteIndex = useSort[0];
        int i = 0;
        for (; i < n-1; i++) {
            useSort[i] = useSort[i + 1];
        }
        useSort[i] = deleteIndex;
    }
    
    private String getKeys() {
		return Arrays.toString(keys);
	}
    private String getValues() {
    	return Arrays.toString(values);
    }
    private String getUseSort() {
    	return Arrays.toString(useSort);
    }
    
    public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println("keys[]:" + cache.getKeys() + "\n"+ 
				"values[]" + cache.getValues() + "\n" + "useSort[]" + cache.getUseSort());
		System.out.println(cache.get(1));
		System.out.println(cache.getUseSort());
		cache.put(3, 3);
		System.out.println("keys[]:" + cache.getKeys() + "\n"+ 
				"values[]" + cache.getValues() + "\n" + "useSort[]" + cache.getUseSort());
		System.out.println(cache.get(2));
		cache.put(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}
}
