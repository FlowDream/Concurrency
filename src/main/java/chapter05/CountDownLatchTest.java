/**
 * 
 */
package chapter05;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch ²âÊÔÑùÀý  
 */
public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("ThreadName:" + Thread.currentThread().getName() + ", begin");
		CountDownLatch latch = new CountDownLatch(5);

		for (int i = 0; i < 5; i++) {
			new MyThread(latch).start();
		}

		latch.await();
		System.out.println("ThreadName:" + Thread.currentThread().getName() + ", end");
	}

	private static class MyThread extends Thread {
		private CountDownLatch latch;

		public MyThread(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			Random random = new Random();
			int value = random.nextInt(10);
			try {
				Thread.sleep(value * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			latch.countDown();
			System.out.println("ThreadName:" + Thread.currentThread().getName() + ", value:" + value);
		}

	}
}
