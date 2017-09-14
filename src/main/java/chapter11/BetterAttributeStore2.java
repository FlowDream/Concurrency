/**
 * 
 */
package chapter11;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 通过将线程安全性委托给其他的类来进一步提升性能
 */
public class BetterAttributeStore2 {
	private final Map<String, String> attributes = new ConcurrentHashMap<>();

	public boolean userLoactionMatches(String name, String regexp) {
		String key = "user." + name + ".location";
		String location = attributes.get(key);

		if (location == null) {
			return false;
		} else {
			return Pattern.matches(regexp, location);
		}
	}
}
