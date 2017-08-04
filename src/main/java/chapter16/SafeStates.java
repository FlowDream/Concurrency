/**
 * 
 */
package chapter16;

import java.util.HashMap;
import java.util.Map;

/**
 * ���ɱ����ĳ�ʼ����ȫ��
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
