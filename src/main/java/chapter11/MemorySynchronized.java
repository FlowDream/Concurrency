/**
 * 
 */
package chapter11;

import java.util.List;
import java.util.Vector;

/**
 * 内存同步
 */
public class MemorySynchronized {

	/**
	 * 没有作用的同步
	 */
	public void testMemorySyn() {

		synchronized (new Object()) {

		}

	}

	/**
	 * 可通过锁消除优化去掉锁获取操作
	 * 
	 * @return
	 */
	public String getStoogeNames() {
		List<String> stooges = new Vector<>();
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
		return stooges.toString();
	}

}
