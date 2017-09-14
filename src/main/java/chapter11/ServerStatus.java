/**
 * 
 */
package chapter11;

import java.util.HashSet;
import java.util.Set;

/**
 * 对锁进行分解
 */
public class ServerStatus {
	public final Set<String> users;
	public final Set<String> queries;

	public ServerStatus() {
		users = new HashSet<>();
		queries = new HashSet<>();
	}

	public synchronized void addUser(String u) {
		users.add(u);
	}

	public synchronized void addQuery(String u) {
		queries.add(u);
	}

	public synchronized void removeUser(String u) {
		users.remove(u);
	}

	public synchronized void removeQuery(String u) {
		queries.remove(u);
	}
}
