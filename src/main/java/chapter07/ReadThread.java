/**
 * 
 */
package chapter07;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 通过改写 interrupt 方法将非标准的取消操作封装在 Thread 中
 */
public class ReadThread extends Thread {
	private static final int BUFSZ = 512;
	private final Socket socket;
	private final InputStream in;

	public ReadThread(Socket socket) throws IOException {
		this.socket = socket;
		this.in = socket.getInputStream();
	}

	@Override
	public void interrupt() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			super.interrupt();
		}
	}

	@Override
	public void run() {
		try {
			byte[] buf = new byte[BUFSZ];

			while (true) {
				int count = in.read(buf);

				if (count < 0) {
					break;
				} else if (count > 0) {
					processBuffer(buf, count);
				}
			}
		} catch (IOException e) {
			// 允许线程退出
			Thread.currentThread().interrupt();
		}
	}

	public void processBuffer(byte[] buf, int count) {

	}
}
