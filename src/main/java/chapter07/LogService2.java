/**
 * 
 */
package chapter07;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 使用 ExecutorService 的日志服务
 */
public class LogService2 {
	private final ExecutorService exec;
	private final PrintWriter writer;

	public LogService2(Writer writer) {
		this.exec = Executors.newSingleThreadExecutor();
		this.writer = new PrintWriter(writer);
	}

	public void stop() throws InterruptedException {
		try {
			exec.shutdown();
			exec.awaitTermination(5, TimeUnit.SECONDS);
		} finally {
			writer.close();
		}
	}

	public void log(String msg) throws InterruptedException {
		try {
			exec.execute(new WriterTask(msg));
		} catch (RejectedExecutionException e) {
		}
	}

	private class WriterTask implements Runnable {
		private String msg;

		public WriterTask(String msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			writer.println(msg);
		}
	}
}
