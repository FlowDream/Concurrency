/**
 * 
 */
package chapter05;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用 Semaphore 为容器设置边界
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
			// 没有添加任何元素，则立刻释放许可
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
