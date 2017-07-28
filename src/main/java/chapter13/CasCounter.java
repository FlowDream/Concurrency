/**
 * 
 */
package chapter13;

/**
 * 基于 CAS 实现的非阻塞计数器
 */
public class CasCounter {
	private SimulateCAS value;

	public int getValue() {
		return value.getValue();
	}

	public int increment() {
		int v;

		do {
			v = value.getValue();
		} while (v != value.compareAndSwap(v, v + 1));

		return v + 1;
	}
}
