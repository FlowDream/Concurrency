/**
 * 
 */
package chapter04;

/**
 * 通过一个私有锁来保护状态
 */
public class ThreadSafeCounter {

	private long value = 0;

	public synchronized long getValue() {
		return value;
	}

	public synchronized void increment() {

		if (value == Long.MAX_VALUE) {
			throw new IllegalStateException();
		}

		value++;
	}

}
