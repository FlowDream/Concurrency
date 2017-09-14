/**
 * 
 */
package chapter11;

import java.util.concurrent.BlockingQueue;

/**
 * ��������еĴ��з���
 */
public class WorkerThread extends Thread {
	private BlockingQueue<Runnable> queue;

	public WorkerThread(BlockingQueue<Runnable> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Runnable task = queue.take();
				task.run();
			} catch (InterruptedException e) {
				// �����߳�
				break;
			}
		}
	}

}
