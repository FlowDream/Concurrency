/**
 * 
 */
package chapter03;

import java.util.HashSet;
import java.util.Set;

/**
 * ����һ������
 */
public class PublishObject {
	
	public static Set<String> knownSet;

	public void init() {
		knownSet = new HashSet<>();
	}
}
