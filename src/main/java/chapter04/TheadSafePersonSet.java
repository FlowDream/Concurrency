/**
 * 
 */
package chapter04;

import java.util.HashSet;
import java.util.Set;

/**
 * ͨ����װ������ȷ���̰߳�ȫ 
 */
public class TheadSafePersonSet {

	private final Set<String> mySet = new HashSet<>();

	public synchronized void addPerson(String person) {
		mySet.add(person);
	}

	public synchronized boolean containsPerson(String person) {
		return mySet.contains(person);
	}
}
