/**
 * 
 */
package chapter14;

/**
 * ʹ����������ʵ�ֵ��н绺��
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
	 * ���ȣ���������ӿձ�Ϊ�ǿգ������תΪ����ʱ������Ҫ�ͷ�һ���̡߳�
	 * ���ң����� put �� take Ӱ�쵽��Щ״̬ת��ʱ���ŷ���֪ͨ��
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
