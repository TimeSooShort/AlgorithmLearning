package Algorithms4th.search;


public class SeparateChainingHash<Key, Value> {
	
	private static final int INIT_CAPACITY = 4;
	
	private int n; //节点总数
	private int m; //数组长度
	private SequentSearchST<Key, Value>[] st;
	
	public SeparateChainingHash() {
		this(INIT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SeparateChainingHash(int m) {
		this.m = m;
		st = (SequentSearchST<Key, Value>[]) new SequentSearchST[m];
		for (int i = 0; i < m; i++) {
			st[i] = new SequentSearchST<>();
		}
	}
	
	public void resize(int newCapacity) {
		SeparateChainingHash<Key, Value> temp = new SeparateChainingHash<>(newCapacity);
		for (int i = 0; i < n; i++) {
			for(Key key: st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.m = temp.m;
		this.n = temp.n;
		this.st = temp.st;
	}
	
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) & m;
	}
	
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}
	
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		int hash = hash(key);
		return st[hash].get(key);
	}
	
	public void put(Key key, Value value) {
		if (key == null) throw new IllegalArgumentException("argument to put() is null");
		if (value == null) {
			delete(key);
			return;
		}
		if (n > 10*m) resize(2*m);
		int hash = hash(key);
		if (!st[hash].contains(key)) n++;
		st[hash].put(key, value);
	}
	
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		
		int hash = hash(key);
		if (st[hash].contains(key)) n--;
		st[hash].delete(key);
		
		if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
	}
	

}
