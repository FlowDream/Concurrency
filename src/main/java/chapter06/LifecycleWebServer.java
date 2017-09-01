/**
 * 
 */
package chapter06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * 支持关闭操作的 Web 服务器
 */
public class LifecycleWebServer {
	private static final ExecutorService pool = Executors.newFixedThreadPool(10);

	public void start() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(80);
		while (!pool.isShutdown()) {
			try {
				final Socket connection = server.accept();

				pool.execute(new Runnable() {

					@Override
					public void run() {
						handleRequest(connection);
					}
				});
			} catch (RejectedExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		pool.shutdown();
	}

	void handleRequest(Socket connection) {

	}
}
