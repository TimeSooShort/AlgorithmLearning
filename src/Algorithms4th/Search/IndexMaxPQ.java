package Algorithms4th.Search;

import java.util.NoSuchElementException;

/**
 * 索引优先队列，在原先基于堆的优先序列上对每个元素增加了索引，可以看成是功能的拓展
 * 比如通过索引改变某个值，或是删除某个值，这些操作复杂度都是log(n)
 * @author Administrator
 *
 * @param <Key>
 */
public class IndexMaxPQ<Key extends Comparable<Key>> {

	private Key[] keys;
	private int[] pq; //存储索引的数组
	private int[] qp; //qp[pq[i]] = i;
	private int n;
	
	public IndexMaxPQ(int maxN) {
		// TODO Auto-generated constructor stub
		keys = (Key[]) new Comparable[maxN + 1];
		pq= new int[maxN + 1];
		qp = new int[maxN+1];
		for (int i = 0; i <=maxN; i++) {
			qp[i] = -1;
		}
	}
	
	/**
	 * 交换pq[i]与pq[j]
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j) {
		int temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	
	private boolean less(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
	}
	
	private void swim(int k) {
		while (k/2 >= 1 && less(k/2, k)) {
			exch(k, k/2);
			k /= 2;
		}
	}
	
	private void sink(int k) {
		while (k*2 <= n) {
			int j = k*2;
			if (j < n && less(j, j+1)) j++; // j<n这步判断很重要，在删除最大数的时候不再将被替换到末尾的索引考虑在内
			if (less(j, k)) break;
			exch(j, k);
			k = j;
		}
	}

	public boolean isEmpty() {
		return n==0;
	}
	
	public int size() {
		return n;
	}
	
	/**
	 * 是否包含该索引
	 * @param i
	 * @return
	 */
	public boolean contains(int i) {
		return qp[i] != -1;
	}
	
	/**
	 * 插入 
	 * @param i 索引值
	 * @param key 插入值
	 */
	public void insert(int i, Key key) {
		if (contains(i)) 
			throw new IllegalArgumentException("index is already in the priority queue");
		n++;
		pq[n] = i;
		qp[i] = n;
		keys[i] = key;
		swim(n);
	}
	
	public int maxIndex() {
		if (n == 0) throw new NoSuchElementException("priority queue underFlow");
		return pq[1];
	}
	
	public Key maxKey() {
		if (n == 0) throw new NoSuchElementException("priority queue underFlow");
		return keys[pq[1]];
	}
	
	/**
	 * 删除最大值
	 * @return 返回被删除的最大值的索引
	 */
	public int delMax() {
		if (n == 0) throw new NoSuchElementException("priority queue underFlow");
		int min = pq[1];
		exch(1, n--);
		sink(1);
		
		assert pq[n+1] == min;
		keys[min] = null;
		pq[n+1] = -1;
		qp[min] = -1;
		return min;
	}
	
	public void changeKey(int i, Key key) {
		if (!contains(i)) 
			throw new NoSuchElementException("index is not in the priority queue");
		keys[i] = key;
		swim(qp[i]);
		sink(qp[i]);
	}
	
	public void delete(int i) {
		if (!contains(i)) 
			throw new NoSuchElementException("index is not in the priority queue");
		int index = qp[i];
		exch(index, n--);
		swim(index);
		sink(index);
		keys[i] = null;
		qp[i] = -1;
	}
}
