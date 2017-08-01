/**
 * 
 */
package chapter05;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 在计时测试中使用 CountDownLatch 来启动和停止线程
 */
public class TestHarness {

	public static void main(String[] args) throws InterruptedException {
		MyThread myThread = new MyThread();
		TestHarness th = new TestHarness();
		System.out.println(th.timeTasks(3, myThread));
	}

	public long timeTasks(int nThreads, Runnable task) throws InterruptedException {
		CountDownLatch startGate = new CountDownLatch(1);
		CountDownLatch endGate = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread() {

				@Override
				public void run() {
					try {
						startGate.await();
						task.run();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						endGate.countDown();
					}
				}

			};

			t.start();
		}

		long begin = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		return (end - begin);
	}

	private static class MyThread implements Runnable {

		@Override
		public void run() {
			Random random = new Random();

			try {
				Thread.sleep(random.nextInt(5));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + ":start");
		}

	}
}
