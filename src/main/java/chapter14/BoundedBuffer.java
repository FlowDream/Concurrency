/**
 * 
 */
package chapter14;

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

	/**
	 * 首先，仅当缓存从空变为非空，或从满转为非满时，才需要释放一个线程。
	 * 并且，仅当 put 或 take 影响到这些状态转换时，才发出通知。
	 * 
	 * @param v
	 * @throws InterruptedException
	 */
	public synchronized void putImprove(V v) throws InterruptedException {
		while (isFull()) {
			wait();
		}

		boolean isEmpty = isEmpty();

		doPut(v);

		if (isEmpty) {
			notifyAll();
		}
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
