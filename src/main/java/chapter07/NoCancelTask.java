/**
 * 
 */
package chapter07;

import java.util.concurrent.BlockingQueue;

/**
 * 不可取消任务在退出前恢复中断
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
