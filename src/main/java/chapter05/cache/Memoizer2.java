/**
 * 
 */
package chapter05.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ʹ�� ConcurrentHashMap ���� HashMap �Ľ�
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
			// ���ĳ���߳�������һ�������ܴ�ļ��㣬�������̲߳���֪������������ڽ��У�
			// ��ô�ܿ��ܻ��ظ��������
			result = c.compute(arg);
			cacheMap.put(arg, result);
		}

		return result;
	}
}
