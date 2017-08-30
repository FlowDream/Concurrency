/**
 * 
 */
package chapter05.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用 ConcurrentHashMap 代替 HashMap 改进
 */
public class Memoizer2<A, V> implements Computable<A, V> {
	private final Map<A, V> cacheMap = new ConcurrentHashMap<>();
	private final Computable<A, V> c;

	public Memoizer2(Computable<A, V> c) {
		this.c = c;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		V result = cacheMap.get(arg);

		if (null == result) {
			// 如果某个线程启动了一个开销很大的计算，而其他线程并不知道这个计算正在进行，
			// 那么很可能会重复这个计算
			result = c.compute(arg);
			cacheMap.put(arg, result);
		}

		return result;
	}
}
