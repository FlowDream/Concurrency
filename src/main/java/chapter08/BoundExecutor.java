/**
 * 
 */
package chapter08;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * ʹ��һ���޽���У��������ź������Ͻ�Ϊ�̳߳ش�С���Ͽ��Ŷ������������ 
 * ������Ϊ�ź�����Ҫ��������ִ�еĺ͵ȴ�ִ�е�����������
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
