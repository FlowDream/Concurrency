/**
 * 
 */
package chapter07;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 通过中断来取消
 */
public class BrokenPrimeProducer2 extends Thread {
	private final BlockingQueue<BigInteger> queue;

	public BrokenPrimeProducer2(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;

		while (!Thread.currentThread().isInterrupted()) {
			try {
				queue.put(p = p.nextProbablePrime());
			} catch (InterruptedException e) {
				// 允许线程退出
				Thread.currentThread().interrupt();
			}
		}
	}

	public void cancel() {
		interrupt();
	}

	public BlockingQueue<BigInteger> get() {
		return queue;
	}

	public static void main(String[] args) {
		BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(1000);
		BrokenPrimeProducer2 bpp = new BrokenPrimeProducer2(queue);
		bpp.start();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bpp.cancel();
		}

		System.out.println(bpp.get());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
