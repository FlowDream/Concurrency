/**
 * 
 */
package chapter12;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试 BoundedBuffer 的生产者 - 消费者程序
 */
public class PutTakeTest {
	protected static final ExecutorService pool = Executors.newCachedThreadPool();
	protected final AtomicInteger putSum = new AtomicInteger(0);
	protected final AtomicInteger takeSum = new AtomicInteger(0);
	protected final BoundedBuffer<Integer> bb;
	protected final int nTrials, nPairs;
	protected CyclicBarrier barrier;

	public PutTakeTest(int capacity, int nTrials, int nPairs) {
		bb = new BoundedBuffer<>(capacity);
		this.nTrials = nTrials;
		this.nPairs = nPairs;
		this.barrier = new CyclicBarrier(nPairs * 2 + 1);
	}

	void test() {
		try {

			for (int i = 0; i < nPairs; i++) {
				pool.execute(new Producer());
				pool.execute(new Consumer());
			}

			barrier.await(); // 等待所有的线程就绪
			barrier.await(); // 等待所有的线程执行完成
			System.out.println(putSum.get() == takeSum.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static int xorShift(int y) {
		y ^= (y << 6);
		y ^= (y >>> 21);
		y ^= (y << 7);
		return y;
	}

	class Producer implements Runnable {

		@Override
		public void run() {
			try {
				int seed = this.hashCode() ^ (int) System.nanoTime();
				int sum = 0;
				barrier.await();

				for (int i = nTrials; i > 0; --i) {
					bb.put(seed);
					sum += seed;
					seed = xorShift(seed);
				}

				putSum.getAndAdd(sum);
				barrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class Consumer implements Runnable {

		@Override
		public void run() {
			try {
				barrier.await();
				int sum = 0;

				for (int i = nTrials; i > 0; --i) {
					sum += bb.take();
				}

				takeSum.getAndAdd(sum);
				barrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws Exception {
		new PutTakeTest(10, 100, 100).test();
		pool.shutdown();
	}

}
