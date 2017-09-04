/**
 * 
 */
package chapter07;

import java.util.concurrent.BlockingQueue;

/**
 * ����ȡ���������˳�ǰ�ָ��ж�
 */
public class NoCancelTask {

	public Task getNextTask(BlockingQueue<Task> queue) {
		boolean interrupted = false;

		try {
			while (true) {
				try {
					return queue.take();
				} catch (InterruptedException e) {
					interrupted = true;
					/* retry */
				}
			}
		} finally {
			if (interrupted) {
				Thread.currentThread().interrupt();
			}
		}
	}

	private class Task {
	};
}
