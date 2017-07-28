/**
 * 
 */
package chapter13;

/**
 * ���� CAS ʵ�ֵķ�����������
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
