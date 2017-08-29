/**
 * 
 */
package chapter05;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * ʹ�� Semaphore Ϊ�������ñ߽�
 */
public class BoundedHashSet<T> {
	private Set<T> set;
	private Semaphore sem;

	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<>(bound));
		sem = new Semaphore(bound);
	}

	public boolean add(T o) throws InterruptedException {
		sem.acquire();
		boolean result = false;

		try {
			result = set.add(o);
			return result;
		} finally {
			// û������κ�Ԫ�أ��������ͷ����
			if (!result) {
				sem.release();
			}
		}
	}

	public boolean remove(Object o) {
		boolean result = set.remove(o);

		if (result) {
			sem.release();
		}

		return result;
	}
}
