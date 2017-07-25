/**
 * 
 */
package chapter13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Lock 来实现信号量
 */
public class SemaphoreOnLock {
	private final Lock lock = new ReentrantLock();
	private final Condition permitsAvailable = lock.newCondition();
	private int permits;

	public SemaphoreOnLock(int permits) {
		try {
			lock.lock();
			this.permits = permits;
		} finally {
			lock.unlock();
		}
	}

	public void acquire() throws InterruptedException {
		try {
			lock.lock();

			while (permits == 0) {
				permitsAvailable.await();
			}

			--permits;
		} finally {
			lock.unlock();
		}
	}

	public void release() {
		try {
			lock.lock();
			++permits;
			permitsAvailable.signal();
		} finally {
			lock.unlock();
		}
	}
}
