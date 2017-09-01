/**
 * 
 */
package chapter06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 串行的web服务器
 * 
 */
public class SingleThreadWebServer {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(80);
		while (true) {
			Socket connection = serverSocket.accept();
			handleRequest(connection);
		}
	}

	private static void handleRequest(Socket connection) {

	}
}
