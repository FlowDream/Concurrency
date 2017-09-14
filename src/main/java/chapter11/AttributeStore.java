/**
 * 
 */
package chapter11;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * ��һ��������Ҫ�س��й���ʱ��
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
