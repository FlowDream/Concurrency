/**
 * 
 */
package chapter14;

/**
 * ʹ�� wait �� notifyAll ��ʵ�ֿ����¹رյķ���
 */
public class ThreadGate {
	private boolean isOpen;
	private int generation;

	public synchronized void close() {
		isOpen = true;
	}

	public synchronized void opean() {
		++generation;
		isOpen = true;
		notifyAll();
	}

	public synchronized void await() throws InterruptedException {
		int arrayvlGeneration = generation;

		while (!isOpen && arrayvlGeneration == generation) {
			wait();
		}
	}
}
