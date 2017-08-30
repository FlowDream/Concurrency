/**
 * 
 */
package chapter05.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 最终实现：使用 putIfAbsent 原子方法解决复合操作漏洞
 */
public class Memoizer4<A, V> implements Computable<A, V> {
	private final Computable<A, V> c;
	private final Map<A, Future<V>> cacheMap = new ConcurrentHashMap<>();

	public Memoizer4(Computable<A, V> c) {
		this.c = c;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		while (true) {
			Future<V> f = cacheMap.get(arg);

			if (null == f) {
				Callable<V> callable = new Callable<V>() {
					@Override
					public V call() throws Exception {
						return c.compute(arg);
					}

				};

				FutureTask<V> task = new FutureTask<>(callable);
				f = cacheMap.putIfAbsent(arg, task);
				if (null == f) {
					f = task;
					task.run();
				}
			}

			try {
				return f.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
				throw new InterruptedException();
			}
		}
	}
}
