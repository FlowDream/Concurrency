/**
 * 
 */
package chapter05;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 线程安全的迭代操作
 */
public class ThreadSafeHiddenIterator {
	private final Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

	public void add(Integer i) {
		set.add(i);
	}

	public void remove(Integer i) {
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
