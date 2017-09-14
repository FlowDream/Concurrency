/**
 * 
 */
package chapter11;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 将一个锁不必要地持有过长时间
 */
public class AttributeStore {
	private final Map<String, String> attributes = new HashMap<>();

	public synchronized boolean userLoactionMatches(String name, String regexp) {
		String key = "user." + name + ".location";
		String location = attributes.get(key);

		if (location == null) {
			return false;
		} else {
			return Pattern.matches(regexp, location);
		}
	}
}
