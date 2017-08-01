/**
 * 
 */
package chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显示条件变量的有界缓存
 */
public class ConditionBoundedBuffer<T> {
	private final Lock lock = new ReentrantLock();
	private final Condition notEmpty = lock.newCondition();
	private final Condition notFull = lock.newCondition();
	private final int BUFFER_SIZE = 100;
	@SuppressWarnings("unchecked")
	private final T[] items = (T[]) new Object[BUFFER_SIZE];
	private int tail, head, count;

	public void put(T x) throws InterruptedException {
		try {
			lock.lock();

			while (count == items.length) {
				notFull.await();
			}

			items[tail] = x;

			if (++tail == items.length) {
				tail = 0;
			}

			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public T take() throws InterruptedException {
		try {
			lock.lock();

			while (count == 0) {
				notEmpty.await();
			}

			T x = items[head];
			items[head] = null;

			if (++head == items.length) {
				head = 0;
			}

			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
}
