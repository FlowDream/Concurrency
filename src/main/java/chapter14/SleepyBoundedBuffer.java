/**
 * 
 */
package chapter14;

/**
 * ʹ�ü�����ʵ�ֵ��н绺��
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	private final int SLEEP_GRANULARITY = 60;

	protected SleepyBoundedBuffer(int capacity) {
		super(capacity);
	}

	public void put(V v) throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!isFull()) {
					doPut(v);
					return;
				}
			}

			Thread.sleep(SLEEP_GRANULARITY);
		}
	}

	public V take() throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!isEmpty()) {
					return doTake();
				}
			}

			Thread.sleep(SLEEP_GRANULARITY);
		}
	}
}