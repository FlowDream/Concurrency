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
 * ʹ�� Map<A, Future<V>> �滻 Map<A, V>
 */
public class Memoizer3<A, V> implements Computable<A, V> {
	private final Computable<A, V> c;
	private final Map<A, Future<V>> cacheMap = new ConcurrentHashMap<>();

	public Memoizer3(Computable<A, V> c) {
		this.c = c;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		Future<V> f = cacheMap.get(arg);

		// ���ϲ�������û������ӣ����ڵײ� Map ������ִ�еģ�����������޷�ͨ��������ȷ��ԭ����
		if (null == f) {
			Callable<V> callable = new Callable<V>() {

				@Override
				public V call() throws Exception {
					return c.compute(arg);
				}

			};

			FutureTask<V> task = new FutureTask<>(callable);
			f = task;
			cacheMap.put(arg, f);
			task.run();
		}

		try {
			return f.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw new InterruptedException();
		}
	}
}
