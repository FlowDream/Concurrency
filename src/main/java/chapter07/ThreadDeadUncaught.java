/**
 * 
 */
package chapter07;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Random;

/**
 * 
 */
public class ThreadDeadUncaught {

	public static void main(String[] args) {
		Producer p = new Producer();
		p.start();
	}

	private static class Producer extends Thread {

		Producer() {
			this.setUncaughtExceptionHandler(new UEHLogger());
		}

		@Override
		public void run() {
			System.out.println("producer beign");
			Random random = new Random();

			while (true) {
				int value = random.nextInt(10);

				if (value % 2 == 0) {
					System.out.println("producer exception");
					throw new IllegalStateException();
				} else {
					System.out.println(value);
				}
			}
		}
	}

	private static class UEHLogger implements UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("Thread terminated with exception:" + t.getName());
		}

	}

}
