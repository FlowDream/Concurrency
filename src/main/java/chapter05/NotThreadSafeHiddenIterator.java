/**
 * 
 */
package chapter05;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 隐藏在字符串连接中的迭代操作(不要这么做)
 */
public class NotThreadSafeHiddenIterator {
	private final Set<Integer> set = new HashSet<>();

	public synchronized void add(Integer i) {
		set.add(i);
	}

	public synchronized void remove(Integer i) {
		set.remove(i);
	}

	public void addTenThings() {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			add(random.nextInt());
			System.out.println("DEBUG:added ten elements to " + set);
		}
	}
}
