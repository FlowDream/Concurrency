/**
 * 
 */
package chapter11;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 减少锁的持有时间
 */
public class BetterAttributeStore {
	private final Map<String, String> attributes = new HashMap<>();

	public boolean userLoactionMatches(String name, String regexp) {
		String key = "user." + name + ".location";
		String location;

		synchronized (this) {
			location = attributes.get(key);
		}

		if (location == null) {
			return false;
		} else {
			return Pattern.matches(regexp, location);
		}
	}
}
