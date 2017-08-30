/**
 * 
 */
package chapter05.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用 HashMap 和同步机制来初始化缓存
 */
public class Memoizer1<A, V> implements Computable<A, V> {
	private final Map<A, V> cacheMap = new HashMap<>();
	private final Computable<A, V> c;

	public Memoizer1(Computable<A, V> c) {
		this.c = c;
	}

	@Override
	public synchronized V compute(A arg) throws InterruptedException {
		// 可以保证线程安全，但因为是对整个方法加锁，会导致多个线程排队等待还未计算的结果
		V result = cacheMap.get(arg);

		if (null == result) {
			result = c.compute(arg);
			cacheMap.put(arg, result);
		}

		return result;
	}
}
