/**
 * 
 */
package chapter05.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * ʹ�� HashMap ��ͬ����������ʼ������
 */
public class Memoizer1<A, V> implements Computable<A, V> {
	private final Map<A, V> cacheMap = new HashMap<>();
	private final Computable<A, V> c;

	public Memoizer1(Computable<A, V> c) {
		this.c = c;
	}

	@Override
	public synchronized V compute(A arg) throws InterruptedException {
		// ���Ա�֤�̰߳�ȫ������Ϊ�Ƕ����������������ᵼ�¶���߳��Ŷӵȴ���δ����Ľ��
		V result = cacheMap.get(arg);

		if (null == result) {
			result = c.compute(arg);
			cacheMap.put(arg, result);
		}

		return result;
	}
}
