/**
 * 
 */
package chapter04;

/**
 * �̰߳�ȫ�ҿɱ�� Point ��
 */
public class ThreadSafePoint {
	private int x, y;

	private ThreadSafePoint(int[] point) {
		this(point[0], point[1]);
	}

	/**
	 * �������캯��
	 * 
	 * @param point
	 */
	public ThreadSafePoint(ThreadSafePoint point) {
		//	����������´���ʵ�֣��������̬������		
		//	this(point.x, point.y);
		this(point.get());
	}

	public ThreadSafePoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public synchronized int[] get() {
		return new int[] { x, y };
	}

	public synchronized void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
