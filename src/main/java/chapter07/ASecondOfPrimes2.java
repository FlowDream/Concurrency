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
 * 通过 Future 来取消任务
 */
public class ASecondOfPrimes2 {
	private static final ExecutorService cancelExec = Executors.newFixedThreadPool(10);

	public static void timeRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
		Future<?> task = cancelExec.submit(r);

		try {
			task.get(timeout, unit);
		} catch (TimeoutException e) {
			// 接下来任务将被取消
		} catch (ExecutionException e) {
			// 如果在任务中抛出了异常，那么重新抛出该异常
			throw LaunderThrowable.launderThrowable(e);
		} finally {
			// 如果任务已经结束，那么执行取消操作也不会带来任何影响
			// 如果任务正在运行，那么将被中断
			task.cancel(true);
		}
	}
}
