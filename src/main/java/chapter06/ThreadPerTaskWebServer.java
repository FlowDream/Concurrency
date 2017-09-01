/**
 * 
 */
package chapter06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �� Web ��������Ϊÿ����������һ���µ��߳�
 */
public class ThreadPerTaskWebServer {

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

			new Thread(task).start();
		}
	}

	private static void handleRequest(Socket connection) {

	}
}
