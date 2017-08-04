/**
 * 
 */
package chapter16;

/**
 * 不安全的延迟初始化
 */
public class UnsafeLazyInitialization {
	private static Resource resource;

	public static Resource getInstance() {
		if (resource == null) {
			resource = new Resource();
		}

		return resource;
	}

	private static class Resource {

	}
}
