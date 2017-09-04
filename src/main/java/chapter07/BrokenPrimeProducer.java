/**
 * 
 */
package chapter07;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 不可靠的取消操作将把生产者置于阻塞的操作中
 */
public class BrokenPrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled;

	public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;

		while (!cancelled) {
			try {
				queue.put(p = p.nextProbablePrime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public BlockingQueue<BigInteger> get() {
		return queue;
	}

	public static void main(String[] args) {
		BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(1000);
		BrokenPrimeProducer bpp = new BrokenPrimeProducer(queue);
		bpp.start();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bpp.cancel();
		}

		System.out.println(bpp.get());
	}
}
