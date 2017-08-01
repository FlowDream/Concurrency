/**
 * 
 */
package chapter14;

/**
 * 使用 wait 和 notifyAll 来实现可重新关闭的阀门
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
