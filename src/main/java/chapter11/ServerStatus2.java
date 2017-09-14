/**
 * 
 */
package chapter11;

import java.util.HashSet;
import java.util.Set;

/**
 * 将 ServerStatus 重新改写为使用锁分解技术
 */
public class ServerStatus2 {
	public final Set<String> users;
	public final Set<String> queries;

	public ServerStatus2() {
		users = new HashSet<>();
		queries = new HashSet<>();
	}

	public void addUser(String u) {
		synchronized (users) {
			users.add(u);
		}
	}

	public void addQuery(String u) {
		synchronized (queries) {
			queries.add(u);
		}
	}

	public void removeUser(String u) {
		synchronized (users) {
			users.remove(u);
		}
	}

	public void removeQuery(String u) {
		synchronized (queries) {
			queries.remove(u);
		}
	}
}
