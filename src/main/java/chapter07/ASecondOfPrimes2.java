/**
 * 
 */
package chapter07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ͨ�� Future ��ȡ������
 */
public class ASecondOfPrimes2 {
	private static final ExecutorService cancelExec = Executors.newFixedThreadPool(10);

	public static void timeRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
		Future<?> task = cancelExec.submit(r);

		try {
			task.get(timeout, unit);
		} catch (TimeoutException e) {
			// ���������񽫱�ȡ��
		} catch (ExecutionException e) {
			// ������������׳����쳣����ô�����׳����쳣
			throw LaunderThrowable.launderThrowable(e);
		} finally {
			// ��������Ѿ���������ôִ��ȡ������Ҳ��������κ�Ӱ��
			// ��������������У���ô�����ж�
			task.cancel(true);
		}
	}
}
