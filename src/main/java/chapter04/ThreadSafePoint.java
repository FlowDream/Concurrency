/**
 * 
 */
package chapter04;

/**
 * 线程安全且可变的 Point 类
 */
public class ThreadSafePoint {
	private int x, y;

	private ThreadSafePoint(int[] point) {
		this(point[0], point[1]);
	}

	/**
	 * 拷贝构造函数
	 * 
	 * @param point
	 */
	public ThreadSafePoint(ThreadSafePoint point) {
		//	如果按照如下代码实现，会产生竞态条件？		
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
