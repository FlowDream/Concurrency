/**
 * 
 */
package chapter04;

/**
 *
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
