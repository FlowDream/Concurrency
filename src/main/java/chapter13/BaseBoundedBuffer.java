/**
 * 
 */
package chapter13;

/**
 * 有界缓存实现的基类
 */
public class BaseBoundedBuffer<V> {
	private final V[] buf;
	private int tail;
	private int head;
	private int count;

	@SuppressWarnings("unchecked")
	protected BaseBoundedBuffer(int capacity) {
		buf = (V[]) new Object[capacity];
	}

	protected synchronized void doPut(V v) {
		buf[tail] = v;

		if (++tail == buf.length) {
			tail = 0;
		}

		++count;
	}

	protected synchronized V doTake() {
		V v = buf[head];
		buf[head] = null;

		if (++head == buf.length) {
			head = 0;
		}

		--count;
		return v;
	}

	public synchronized boolean isFull() {
		return count == buf.length;
	}

	public synchronized boolean isEmpty() {
		return count == 0;
	}
}
