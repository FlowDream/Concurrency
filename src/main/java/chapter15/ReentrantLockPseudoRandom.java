/**
 * 
 */
package chapter15;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 ReentrantLock 实现的随机数生成器
 */
public class ReentrantLockPseudoRandom extends PseudoRandom {
	private final ReentrantLock lock = new ReentrantLock(false);
	private int seed;

	public ReentrantLockPseudoRandom(int seed) {
		this.seed = seed;
	}

	public int nextInt(int n) {
		lock.lock();
		try {
			int s = seed;
			seed = calculateNext(n);
			int remainder = s % n;
			return remainder > 0 ? remainder : remainder + n;
		} finally {
			lock.unlock();
		}
	}

}
