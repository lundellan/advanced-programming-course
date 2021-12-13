package map;

import java.util.LinkedList;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private int capacity;
	private double load;
	private LinkedList<Entry<K, V>> list;
	private Entry[] table;
	private int size;
	
	
	public SimpleHashMap() {
		this(16);
	}

	public SimpleHashMap(int capacity) {
		load = 0.75;
		this.capacity = capacity;
		size = 0;
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	@Override
	public V get(Object arg0) {
		K key = (K) arg0;
		Entry<K, V> entry = find(index(key), key);
		if (entry != null) {
			return entry.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public V put(K key, V value) {
		Entry<K, V> entry = new Entry<K, V>(key, value);
		int index = index(key);
		
		if (table[index] == null) {
			table[index] = entry;
		} else {
			entry = find(index, key);
			if (entry != null) {
				V old = entry.getValue();
				entry.setValue(value);
				return old;
			}

			entry = table[index];
			while (entry.next != null) {
				entry = entry.next;
			}
			entry.next = new Entry<K, V>(key, value);
		}
		size++;
		if (size > capacity * load) {
			capacity = capacity * 2;
			rehash();
		}
		return null;
	}

	private void rehash() {
		Entry<K, V>[] old = table;
		table = (Entry<K, V>[]) new Entry[capacity];
		size = 0;

		for (Entry<K, V> entry : old) {
			while (entry != null) {
				put(entry.getKey(), entry.getValue());
				entry = entry.next;
			}
		}

	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		int index = index(key);
		if (table[index] == null)
			return null;
		else if (table[index].getKey().equals(key)) {
			Entry<K, V> e = table[index];
			table[index] = e.next;
			size--;
			return e.getValue();
		} else {
			Entry<K, V> preElement = table[index];
			Entry<K, V> curElement = table[index].next;
			while (curElement != null) {
				if (curElement.getKey().equals(key)) {
					preElement.next = curElement.next;
					size--;
					return curElement.getValue();
				}
				preElement = curElement;
				curElement = curElement.next;
			}
			return null;
		}
	}

	@Override
	public int size() {
		return size;
	}
	
	public String show() {
		StringBuilder sb = new StringBuilder();
		int i = 0;

		for (Entry<K, V> entry : table) {
			sb.append(i);

			while (entry != null) {
				sb.append("\t");
				sb.append(entry.toString());
				entry = entry.next;
			}
			sb.append("\n");
			i++;
		}
		return sb.toString();
	}
	
	private int index(K key) {
		return Math.abs(key.hashCode() % capacity);
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> entry = table[index];
		while (entry != null) {
			if (entry.getKey().equals(key)) {
				return entry;
			}
			entry = entry.next;

		}
		return null;
	}
	
	
	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		private Entry(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}

		public String toString() {
			return key + "=" + value;
		}

	}

}
