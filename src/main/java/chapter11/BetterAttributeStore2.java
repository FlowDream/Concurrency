/**
 * 
 */
package chapter11;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * ͨ�����̰߳�ȫ��ί�и�������������һ����������
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
