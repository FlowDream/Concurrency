/**
 * 
 */
package chapter16;

/**
 * 双重检查加锁（不要这么做）
 */
public class DoubleCheckedLocking {
	private static Resource resource;

	public static Resource getInstance() {
		if (resource == null) {
			synchronized (DoubleCheckedLocking.class) {
				if (resource == null) {
					resource = new Resource();
				}
			}
		}
		
		return resource;
	}

	private static class Resource {

	}
}
