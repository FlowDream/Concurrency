/**
 * 
 */
package chapter07;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 使用私有的 Executor，并且该 Executor 的生命周期受限于方法调用
 */
public class CheckMail {

	public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();

		final AtomicBoolean hasNewMail = new AtomicBoolean(false);

		try {
			for (String host : hosts) {
				exec.execute(new Runnable() {

					@Override
					public void run() {
						if (checkMail(host)) {
							hasNewMail.set(true);
						}
					}
				});

			}
		} finally {
			exec.shutdown();
			exec.awaitTermination(timeout, unit);
		}

		return hasNewMail.get();
	}

	private boolean checkMail(String host) {
		return false;
	}
}
