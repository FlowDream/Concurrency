/**
 * 
 */
package chapter03;

import java.util.HashSet;
import java.util.Set;

/**
 * ����һ������
 * 
 * @author hzguantao
 *
 */
public class PublishObject {
	
	public static Set<String> knownSet;

	public void init() {
		knownSet = new HashSet<>();
	}
}
