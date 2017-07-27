/**
 * 
 */
package chapter04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ͨ���ͻ��˼�����ʵ�� "��û�������"
 */
public class ThreadSafeClienLock {
	public List<String> list = Collections.synchronizedList(new ArrayList<>());

	public boolean putIfAbsent(String e) {

		synchronized (list) {
			if (!list.contains(e)) {
				return list.add(e);
			}

			return false;
		}
	}
}
