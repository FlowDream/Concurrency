/**
 * 
 */
package chapter13;

/**
 * 使用条件队列实现的有界缓存
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected BoundedBuffer(int capacity) {
		super(capacity);
	}

	public synchronized void put(V v) throws InterruptedException {
		while (isFull()) {
			wait();
		}

		doPut(v);
		notifyAll();
	}

	public synchronized V take() throws InterruptedException {
		while (isEmpty()) {
			wait();
		}

		V v = doTake();
		notifyAll();
		return v;
	}
}
