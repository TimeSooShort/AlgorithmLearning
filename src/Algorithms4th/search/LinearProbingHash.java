package Algorithms4th.search;

public class LinearProbingHash<Key, Value> {

	private static final int INIT_CAPACITY = 4;
	
	private int m; //数组大小
	private int n; //元素个数
	private Key[] keys;
	private Value[] values;
	
	public LinearProbingHash() {
		this(INIT_CAPACITY);
	}

	public LinearProbingHash(int capacity) {
		m = capacity;
		n = 0;
		keys = (Key[]) new Object[m];
		values = (Value[]) new Object[m];
	}
	
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}
	
	private void resize(int capacity) {
		LinearProbingHash<Key, Value> temp = new LinearProbingHash<>(capacity);
		for (int i = 0; i < m; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], values[i]);;
			}
		}
		this.keys = temp.keys;
		this.values = temp.values;
		this.m = temp.m;
	}
	
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}
	
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (key.equals(keys[i])) {
				return values[i];
			}
		}
		return null;
	}
	
	public void put(Key key, Value value) {
		if (key == null) throw new IllegalArgumentException("argument to put() is null");
		if (value == null) {
			delete(key);
			return;
		}
		if (n >= m/2) resize(2*m);
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
			 if (key.equals(keys[i])) {
				 values[i] = value;
				 return;
			 }
		}
		keys[i] = key;
		values[i] = value;
		n++;
	}
	
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return;
		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (i + 1) % m;
		}
		keys[i] = null;
		values[i] = null;
		i = (i + 1) % m;
		while(keys[i] != null) {
			Key oldKey = keys[i];
			Value oldValue = values[i];
			keys[i] = null;
			values[i] = null;
			n--;
			put(oldKey, oldValue);
			i = (i + 1) % m;
		}
		n--;
		if (n > 0 && n <= m/8) resize(m/2);
		assert check();
	}
	
	private boolean check() {
		if (m < 2*n) {
			System.err.println("实际元素个数： " + n + "; 数组大小 " + m);
			return false;
		}
		
		for (int i = 0; i < m; i++) {
			if (keys[i] == null) continue;
			else if(get(keys[i]) != values[i]) {
				System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + values[i]);
				return false;
			}
		}
		return true;
	}

}
