/**
 * 
 */
package chapter11;

/**
 * 在基于散列的 Map 中使用锁分段技术
 */
public class StipeMap {
	// 同步策略：buckets[n] 由 locks[n % N_LOCKS] 来保护
	private static final int N_LOCKS = 16;
	private final Node[] buckets;
	private final Object[] locks;

	public StipeMap(int numBuckets) {
		this.buckets = new Node[numBuckets];
		this.locks = new Object[N_LOCKS];

		for (int i = 0; i < locks.length; i++) {
			locks[i] = new Object();
		}
	}

	public Object get(Object key) {
		int hash = hash(key);

		synchronized (locks[hash % N_LOCKS]) {
			for (Node m = buckets[hash]; m != null; m = m.next) {
				if (m.key.equals(key)) {
					return m.value;
				}
			}

			return null;
		}
	}

	public void clear() {
		for (int i = 0; i < buckets.length; i++) {
			synchronized (locks[i % N_LOCKS]) {
				buckets[i] = null;
			}
		}
	}

	private final int hash(Object key) {
		return Math.abs(key.hashCode() % buckets.length);
	}

	private static class Node {
		Node next;
		Object key;
		Object value;
	}
}
