/**
 * 
 */
package chapter07;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 向 LogWriter 添加可靠的取消操作
 */
public class LogService {
	private final BlockingQueue<String> queue;
	private final LoggerThread thread;
	private final PrintWriter writer;
	private boolean isShutDown;
	private int reservations;

	public LogService(Writer writer) {
		this.queue = new LinkedBlockingQueue<>();
		this.thread = new LoggerThread();
		this.writer = new PrintWriter(writer);
	}

	public void start() {
		thread.start();
	}

	public void stop() {
		synchronized (this) {
			isShutDown = true;
		}

		thread.interrupt();
	}

	public void log(String msg) throws InterruptedException {
		synchronized (this) {
			if (isShutDown) {
				throw new IllegalStateException();
			}

			reservations++;
		}

		queue.put(msg);
	}

	private class LoggerThread extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					synchronized (LogService.this) {
						if (isShutDown && reservations == 0) {
							break;
						}
					}

					String msg = queue.take();

					synchronized (LogService.this) {
						reservations--;
					}

					writer.println(msg);
				} catch (InterruptedException e) {
					/* retry */
				} finally {
					writer.close();
				}
			}
		}
	}
}
