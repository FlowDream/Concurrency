/**
 * 
 */
package chapter15;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ���� ReentrantLock ʵ�ֵ������������
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
