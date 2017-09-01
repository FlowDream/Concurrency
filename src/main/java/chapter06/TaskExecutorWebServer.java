/**
 * 
 */
package chapter06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 基于线程池的Web服务器
 */
public class TaskExecutorWebServer {
	private static final int NTHREADS = 100;
	private static final Executor pool = Executors.newFixedThreadPool(NTHREADS);

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(80);

		while (true) {
			final Socket connection = server.accept();

			Runnable task = new Runnable() {

				@Override
				public void run() {
					handleRequest(connection);
				}
			};

			pool.execute(task);
		}
	}

	private static void handleRequest(Socket connection) {

	}

}
