/**
 * 
 */
package chapter16;

import java.util.HashMap;
import java.util.Map;

/**
 * 不可变对象的初始化安全性
 */
public class SafeStates {
	private final Map<String, String> states;

	public SafeStates() {
		states = new HashMap<String, String>();
		states.put("alaska", "AK");
		states.put("alabama", "AL");
	}

	public String getAbbreviation(String s) {
		return states.get(s);
	}
}
