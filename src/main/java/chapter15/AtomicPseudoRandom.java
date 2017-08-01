/**
 * 
 */
package chapter15;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于 AtomicInteger 实现的随机数生成器
 */
public class AtomicPseudoRandom extends PseudoRandom {
	private AtomicInteger seed;

	public AtomicPseudoRandom(int seed) {
		this.seed = new AtomicInteger(seed);
	}

	public int nextInt(int n) {
		while (true) {
			int s = seed.get();
			int nextSeed = calculateNext(n);

			if (seed.compareAndSet(s, nextSeed)) {
				int remainder = s % n;
				return remainder > 0 ? remainder : remainder + n;
			}
		}
	}

}
