/**
 * 
 */
package chapter03;

import java.util.HashSet;
import java.util.Set;

/**
 * 发布一个对象
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
