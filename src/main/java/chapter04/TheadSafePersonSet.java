/**
 * 
 */
package chapter04;

import java.util.HashSet;
import java.util.Set;

/**
 * 通过封装机制来确保线程安全 
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
