/**
 * 
 */
package chapter16;

/**
 * 线程安全的延迟初始化
 */
public class SafeLazyInitialization {
	private static Resource resource;

	public static synchronized Resource getInstance() {
		if (resource == null) {
			resource = new Resource();
		}

		return resource;
	}

	private static class Resource {

	}
}
