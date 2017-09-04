/**
 * 
 */
package chapter07;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用 volatile 类型的域来保存取消状态
 */
public class PrimeGenerator implements Runnable {

	private final List<BigInteger> prime = new ArrayList<>();

	private volatile boolean cancelled;

	@Override
	public void run() {
		BigInteger big = BigInteger.ONE;

		while (!cancelled) {
			big = big.nextProbablePrime();

			synchronized (this) {
				prime.add(big);
			}
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public synchronized List<BigInteger> get() {
		return new ArrayList<>(prime);
	}

	public static void main(String[] args) {
		PrimeGenerator pg = new PrimeGenerator();
		Thread thread1 = new Thread(pg);
		thread1.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			pg.cancel();
		}

		System.out.println(pg.get());
	}
}
