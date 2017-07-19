/**
 * 
 */
package chapter13;

/**
 * 当不满足前提条件时，有界缓存不会执行相应的操作
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}

	public synchronized void put(V v) {
		if (isFull()) {
			throw new BufferFullException();
		}

		doPut(v);
	}

	public synchronized V take() {
		if (isEmpty()) {
			throw new BufferEmptyException();
		}

		return doTake();
	}
}

class BufferFullException extends RuntimeException {
	private static final long serialVersionUID = -8192027330087240222L;
}

class BufferEmptyException extends RuntimeException {
	private static final long serialVersionUID = -4372107178513288009L;
}

class ExampleUsage {
	private GrumpyBoundedBuffer<String> buffer;
	int SLEEP_GRANULARITY = 50;

	void useBuffer() throws InterruptedException {
		while (true) {
			try {
				String item = buffer.take();
				System.out.println(item);
				break;
			} catch (BufferEmptyException e) {
				Thread.sleep(SLEEP_GRANULARITY);
			}
		}
	}
}