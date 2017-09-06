/**
 * 
 */
package chapter08;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 使用一个无界队列，并设置信号量的上界为线程池大小加上可排队任务的数量， 
 * 这是因为信号量需要控制正在执行的和等待执行的任务数量。
 */
public class BoundExecutor {
	private final Executor exec;
	private final Semaphore semaphore;

	public BoundExecutor(Executor exec, int bound) {
		this.exec = exec;
		this.semaphore = new Semaphore(bound);
	}

	public void submitTask(final Runnable command) throws InterruptedException {
		try {
			semaphore.acquire();

			exec.execute(new Runnable() {

				@Override
				public void run() {
					try {
						command.run();
					} finally {
						semaphore.release();
					}
				}
			});

		} catch (RejectedExecutionException e) {
			semaphore.release();
		}
	}

}
